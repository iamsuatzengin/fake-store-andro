package com.example.fakestoreandro.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RatingUIModel(
    val rate: Double? = null,
    val count: Int? = null,
) : Parcelable
