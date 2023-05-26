package com.example.fakestoreandro.model

data class ProductUIModel(
    val id: Int? = null,
    val title: String? = null,
    val price: Double? = null,
    val imageUrl: String? = null,
    val rating: RatingUIModel? = null,
)
