package com.example.fakestoreandro.mapper

import com.example.fakestoreandro.data.model.ProductDto
import com.example.fakestoreandro.domain.model.ProductUIModel

fun ProductDto.toProductUIModel() = ProductUIModel(
    id = id ?: 0,
    title = title ?: "",
    price = price ?: 0.0,
    imageUrl = imageUrl ?: "",
    rating = rating?.toRatingUIModel(),
    category = category ?: ""
)

fun List<ProductDto>.toProductUIModel() = map(ProductDto::toProductUIModel)