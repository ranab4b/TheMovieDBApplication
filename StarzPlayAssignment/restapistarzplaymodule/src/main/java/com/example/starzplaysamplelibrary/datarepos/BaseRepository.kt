package com.example.starzplaysamplelibrary.datarepos


import android.util.Log
import com.example.starzplaysamplelibrary.BaseRS
import com.example.starzplaysamplelibrary.Either
import com.example.starzplaysamplelibrary.MyException

import java.net.UnknownHostException

open class BaseRepository {


    suspend fun <R> either(data: suspend () -> R): Either<MyException, R> {
        return try {


            Either.Right(data.invoke().apply {
                //This check could be handeled if api respons in 200 ok with errror mesage to handle 4001 or any other nee to handel saperately current structure supports to display
                // errrors or success only in 200 response code
//                if (this is BaseRS && this.errors!!.isNotEmpty()) {
//                    return Either.Left(
//                        MyException.ApiErrorError(
//                            Throwable(
//                                this.errors.get(0)
//                            )
//                        )
//                    )
//                }
            })
        } catch (e: Exception) {


            return Either.Left(convertToMyException(e))
        }

    }

    private fun convertToMyException(e: Exception): MyException {
        return when (e) {
            is UnknownHostException -> MyException.NetworkErrorError(e)

            else -> MyException.UnKnownError(e)
        }
    }
}