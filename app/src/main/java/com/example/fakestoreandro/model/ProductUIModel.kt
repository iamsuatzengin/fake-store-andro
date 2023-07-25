package com.example.fakestoreandro.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductUIModel(
    val id: Int = 0,
    val title: String = "",
    val price: Double = 0.0,
    val imageUrl: String = "",
    val rating: RatingUIModel? = null,
    val category: String = "",
) : Parcelable
