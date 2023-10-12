package com.example.mapper

import com.example.model.ProductDto
import com.example.ui.model.ProductUIModel

fun ProductDto.toProductUIModel() = ProductUIModel(
    id = id ?: 0,
    title = title ?: "",
    price = price ?: 0.0,
    imageUrl = imageUrl ?: "",
    rating = rating?.toRatingUIModel(),
    category = category ?: ""
)

fun List<ProductDto>.toProductUIModel() = map(ProductDto::toProductUIModel)