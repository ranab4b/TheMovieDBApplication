package com.example.starzplaysamplelibrary.core

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.akexorcist.localizationactivity.ui.LocalizationActivity
import com.example.starzplaysamplelibrary.MyException
import com.example.starzplaysamplelibrary.utils.Consts
import com.github.loadingview.LoadingDialog


import com.pixplicity.easyprefs.library.Prefs
import materialDialog


abstract class BaseActivity<T : BaseViewModel> : LocalizationActivity() {

    private lateinit var baseViewModel: T

    abstract fun getViewModel(): T

    var loadingDialog: LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseViewModel = getViewModel()
        baseViewModel.progressLiveData.value = false

        baseViewModel.progressLiveData.observe(this, Observer {
            if (it) {
                if (loadingDialog == null)
                    loadingDialog = LoadingDialog.get(this).show()
                else
                    loadingDialog?.show()
            } else {
                loadingDialog?.hide()
            }

        })

        baseViewModel.errorLiveData.observe(this, Observer {
            baseViewModel.progressLiveData.value = false
            loadingDialog?.hide()
            it?.apply {
                if (it is MyException) {
                    when (it) {
                        is MyException.UnKnownError -> {
                            materialDialog(it.throwable.localizedMessage.orEmpty() + "\nNo Results Found")
                        }

                        is MyException.NetworkErrorError -> {
                            Toast.makeText(this@BaseActivity, "Error", Toast.LENGTH_SHORT)
                                .show()
                        }
                        is MyException.ApiErrorError -> {
                            materialDialog(it.throwable.localizedMessage.orEmpty())
                        }
                        is MyException.IAgreeException -> TODO()
                        else -> {
                            // materialDialog(it.throwable.localizedMessage.orEmpty())
                        }
                    }
                } else {
                    Log.e("Error", it.localizedMessage, it)
                }
            }
        })
    }

    fun getLanguageInt(): Int {

        return if (Prefs.getString(
                Consts.PREF_LANGUAGE,
                Consts.PREF_LANGUAGE_EN
            ) == Consts.PREF_LANGUAGE_EN
        ) {
            1 //english
        } else {
            2 // arabic
        }
    }


    fun getUserId(): String {

        return Prefs.getString(Consts.PREF_USER_ID, "")
    }

    override fun onResume() {
        super.onResume()

    }


    override fun onPause() {
        baseViewModel.errorLiveData.value = null
        baseViewModel.progressLiveData.value = false
        super.onPause()
    }


}