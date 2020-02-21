package com.example.starzplaysamplelibrary.core

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.starzplaysamplelibrary.Either
import com.example.starzplaysamplelibrary.MyException

import com.example.starzplaysamplelibrary.utils.Consts
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.coroutines.*
import org.koin.standalone.KoinComponent
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel(), KoinComponent, CoroutineScope {


    val progressLiveData by lazy { MutableLiveData<Boolean>() }
    val errorLiveData by lazy { MutableLiveData<Exception>() }

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override val coroutineContext: CoroutineContext
        get() = viewModelJob


    fun <R> postData(observer: MutableLiveData<R>, result: suspend () -> Either<MyException, R>) {
        progressLiveData.postValue(false)
        launch {
            progressLiveData.postValue(true)
            result.invoke().either({
                progressLiveData.postValue(false)
                errorLiveData.postValue(it)
            }, {
                progressLiveData.postValue(false)
                observer.postValue(it)
            })
        }


    }


    //if want to ignore the result
    fun <R> postNullData(observer: MutableLiveData<R>, result: suspend () -> Either<MyException, R>) {
        progressLiveData.postValue(false)
        launch {
            progressLiveData.postValue(true)
            result.invoke().either({
                progressLiveData.postValue(false)
                errorLiveData.postValue(it)
                observer.postValue(null)
            }, {
                progressLiveData.postValue(false)
                observer.postValue(it)
            })
        }


    }

    fun getLanguageInt(): Int {

        return if (Prefs.getString(Consts.PREF_LANGUAGE, Consts.PREF_LANGUAGE_EN) == Consts.PREF_LANGUAGE_EN) {
            1
        } else {
            2
        }
    }


    fun getUserId(): String {

        return  Prefs.getString(Consts.PREF_USER_ID, "")
    }

    override fun onCleared() {
        viewModelJob.cancel()
        uiScope.coroutineContext.cancelChildren()
        super.onCleared()
    }
}