package com.example.starzplaysamplelibrary.network

import com.example.starzplaysamplelibrary.BaseRS

import com.example.starzplaysamplelibrary.utils.Consts

import io.reactivex.Single

import retrofit2.http.*


interface NetworkService {

    // this could be made more generic we can make all parameters optional so when implementing from activity it would be in developers hand to pass variables currentl i have done only query parameter dynamic
    @GET("search/multi?api_key=${Consts.EXTRA_API_KEY}")
    fun MultiSearch(
        @Query("query") query: String,
        @Query("page") page: Int = 1,
        @Query("include_adult") include_adult: Boolean=false,
        @Query("language") language: String="enen-US"
        ): Single<BaseRS>


}

