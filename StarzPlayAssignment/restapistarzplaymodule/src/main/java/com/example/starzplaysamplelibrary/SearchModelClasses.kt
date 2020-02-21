package com.example.starzplaysamplelibrary

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
open class BaseRS(
    @SerializedName("msg")
    val msg: String = "",
    @SerializedName("errors")
    val errors: List<String?>? = listOf(),
    @SerializedName("status")
    val status: Int = 0,
    @SerializedName("page")
    val page: Int? = 0,
    @SerializedName("results")
    val results: List<Result?>? = listOf(),
    @SerializedName("total_pages")
    val totalPages: Int? = 0,
    @SerializedName("total_results")
    val totalResults: Int? = 0
) : Parcelable

@Parcelize
data class KnownFor(
    @SerializedName("adult")
    val adult: Boolean? = false,
    @SerializedName("backdrop_path")
    val backdropPath: String? = "",
    @SerializedName("genre_ids")
    val genreIds: List<Int?>? = listOf(),
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("media_type")
    val mediaType: String? = "",
    @SerializedName("original_language")
    val originalLanguage: String? = "",
    @SerializedName("original_title")
    val originalTitle: String? = "",
    @SerializedName("overview")
    val overview: String? = "",
    @SerializedName("poster_path")
    val posterPath: String? = "",
    @SerializedName("release_date")
    val releaseDate: String? = "",
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("video")
    val video: Boolean? = false,
    @SerializedName("vote_average")
    val voteAverage: Double? = 0.0,
    @SerializedName("vote_count")
    val voteCount: Int? = 0
) : Parcelable


@Parcelize
data class Result(
    @SerializedName("adult")
    val adult: Boolean? = false,
    @SerializedName("backdrop_path")
    val backdropPath: String? = "",
    @SerializedName("first_air_date")
    val firstAirDate: String? = "",
    @SerializedName("gender")
    val gender: Int? = 0,
    @SerializedName("genre_ids")
    val genreIds: List<Int?>? = listOf(),
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("known_for")
    val knownFor: List<KnownFor?>? = listOf(),
    @SerializedName("known_for_department")
    val knownForDepartment: String? = "",
    @SerializedName("media_type")
    val mediaType: String? = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("origin_country")
    val originCountry: List<String?>? = listOf(),
    @SerializedName("original_language")
    val originalLanguage: String? = "",
    @SerializedName("original_name")
    val originalName: String? = "",
    @SerializedName("original_title")
    val originalTitle: String? = "",
    @SerializedName("overview")
    val overview: String? = "",
    @SerializedName("popularity")
    val popularity: Double? = 0.0,
    @SerializedName("poster_path")
    val posterPath: String? = "",
    @SerializedName("profile_path")
    val profilePath: String? = "",
    @SerializedName("release_date")
    val releaseDate: String? = "",
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("video")
    val video: Boolean? = false,
    @SerializedName("vote_average")
    val voteAverage: Double? = 0.0,
    @SerializedName("vote_count")
    val voteCount: Int? = 0
) : Parcelable
