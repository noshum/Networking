package ua.shumov.networking.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieModel(
    val title: String,
    val image: String,
    val overview: String,
    val vote_average: String
):Parcelable