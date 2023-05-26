package com.example.fakestoreandro.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    @SerialName("id")
    val id: Int? = null,

    @SerialName("title")
    val title: String? = null,

    @SerialName("price")
    val price: Double? = null,

    @SerialName("description")
    val description: String? = null,

    @SerialName("category")
    val category: String? = null,

    @SerialName("image")
    val imageUrl: String? = null,

    @SerialName("rating")
    val rating: RatingDto? = null,
)