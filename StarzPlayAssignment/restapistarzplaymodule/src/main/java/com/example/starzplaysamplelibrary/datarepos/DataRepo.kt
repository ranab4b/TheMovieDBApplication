package com.example.starzplaysamplelibrary.datarepos

import com.example.loginwithkoin.*
import com.example.starzplaysamplelibrary.BaseRS
import com.example.starzplaysamplelibrary.Contracts
import com.example.starzplaysamplelibrary.Either
import com.example.starzplaysamplelibrary.MyException
import com.example.starzplaysamplelibrary.datasource.DataSource

import kotlinx.coroutines.rx2.await
import org.json.JSONObject

class DataRepo constructor(private val authenticateSource: DataSource) : BaseRepository(),
    Contracts {
    override suspend fun MultiSearch(
        query: String
    ): Either<MyException, BaseRS> = either {
        authenticateSource.MultiSearch(query).await()
    }

}