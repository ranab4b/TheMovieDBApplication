package com.example.starzplayassignment.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class RecyclerItemModel(var imageUrl: String, var mTitle: String,var description:String,var type:String):Parcelable