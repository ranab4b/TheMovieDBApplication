package com.example.starzplaysamplelibrary.datasource

import com.example.starzplaysamplelibrary.BaseRS

import com.example.starzplaysamplelibrary.network.NetworkService


import io.reactivex.Single


class DatasourceRS constructor(private val networkService: NetworkService) :
    DataSource {


    override fun MultiSearch(query: String): Single<BaseRS> {
        return networkService.MultiSearch(query)
    }


}