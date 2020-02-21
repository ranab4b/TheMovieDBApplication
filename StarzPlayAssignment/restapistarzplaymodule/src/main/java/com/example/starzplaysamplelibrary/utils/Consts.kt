package com.example.starzplaysamplelibrary.utils



object Consts {

    const val IS_MOBILE = "IS_MOBILE"

    const val DD_MM_YYYY = "dd/MM/yyyy"
    const val IS_Movie = "movie"
    const val IS_Tv = "tv"

    const val EXTRA_API_KEY = "3d0cda4466f269e793e9283f6ce0b75e"
    const val EXTRA_ImagePAth = "https://image.tmdb.org/t/p/w500"
    const val EXTRA_NAME = "EXTRA_NAME"
    const val EXTRA_FROM_REF = "EXTRA_FROM_REF"
    const val EXTRA_IMAGE = "EXTRA_IMAGE"
    const val EXTRA_TRACK_ID = "EXTRA_TRACK_ID"
    const val EXTRA_DATA = "EXTRA_DATA"
    const val EXTRA_PRODUCT_CONTENT = "EXTRA_PRODUCT_CONTENT"
    const val EXTRA_IS_OAB = "EXTRA_IS_OAB"
    const val EXTRA_FORGET_PASSWORD = "EXTRA_FORGET_PASSWORD"
    const val EXTRA_URL = "EXTRA_URL"
    const val EXTRA_LOGIN_TYPE = "LOGIN_TYPE"
    const val EXTRA_SOCIAL_TOKEN= "SOCIAL_TOKEN"
    const val EXTRA_PHONE= "EXTRA_PHONE"


    const val PREF_LANGUAGE = "LANGUAGE"
    const val PREF_LANGUAGE_EN = "LANGUAGE_EN"
    const val PREF_LANGUAGE_AR = "LANGUAGE_AR"
    const val PREF_USER_ID = "USER_ID"
    const val PREF_USER_DETAIL = "USER_DETAIL"
    const val PREF_REMEMBER = "REMEMBER"
    const val PREF_FIRST_NAME = "FIRST_NAME"
    const val PREF_LAST_NAME = "LAST_NAME"
    const val PREF_USERNAME = "USER_NAME"
    const val PREF_REFERRAL_CODE = "REFERRAL_CODE"
    const val PREF_CART_SIZE = "PREF_CART_SIZE"


    const val INTENT_REQUEST = 1000

//    object Contents {
//        var contentRs: ContentRs? = null
//
//        fun setContentRS(contentRs: ContentRs) {
//            Consts.Contents.contentRs = contentRs
//        }
//    }
}

sealed class LoginType(val name: String) {
    object INSTAGRAM : LoginType("INSTAGRAM")
    object FACEBOOK : LoginType("FACEBOOK")
    object GOOGLE : LoginType("GOOGLE")
}