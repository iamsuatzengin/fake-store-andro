package com.example.fakestoreandro.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductRequest(
    @SerialName("title")
    val title: String,
    @SerialName("price")
    val price: Double,
    @SerialName("description")
    val description: String,
    @SerialName("image")
    val image: String,
    @SerialName("category")
    val category: String,
)