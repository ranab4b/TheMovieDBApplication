package com.example.starzplaysamplelibrary

sealed class MyException(val throwable: Throwable, val msg: String = "") : Exception() {

    class UnKnownError(throwable: Throwable) : MyException(throwable)
    class ApiErrorError(throwable: Throwable) : MyException(throwable)
    class NetworkErrorError(throwable: Throwable) : MyException(throwable)
    class IAgreeException(throwable: Throwable): MyException(throwable)
}