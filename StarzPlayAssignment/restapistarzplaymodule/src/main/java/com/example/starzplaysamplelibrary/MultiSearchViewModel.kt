package com.example.starzplaysamplelibrary

import androidx.lifecycle.MutableLiveData
import com.example.starzplaysamplelibrary.core.BaseViewModel
import com.example.starzplaysamplelibrary.map
import org.json.JSONObject
import org.koin.standalone.inject

class MultiSearchViewModel : BaseViewModel() {

    val multiSearchLiveData by lazy { MutableLiveData<BaseRS>() }
    val contract by inject<Contracts>()


    fun MultiSearch(query: String) {
        postData(multiSearchLiveData) {
            contract.MultiSearch(query).map {
                it
            }
        }
    }

}