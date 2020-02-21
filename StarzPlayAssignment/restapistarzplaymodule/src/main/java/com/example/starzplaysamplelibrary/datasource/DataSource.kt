package com.example.starzplaysamplelibrary.datasource

import com.example.starzplaysamplelibrary.BaseRS

import com.google.gson.JsonObject

import io.reactivex.Single
import org.json.JSONObject

interface DataSource {




    fun MultiSearch(query: String): Single<BaseRS>


}