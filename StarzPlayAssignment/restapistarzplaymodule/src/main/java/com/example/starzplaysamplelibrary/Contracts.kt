package com.example.starzplaysamplelibrary

import org.json.JSONObject


interface Contracts {


    suspend fun MultiSearch(
        query: String
    ): Either<MyException, BaseRS>


}