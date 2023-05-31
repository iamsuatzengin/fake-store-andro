package com.example.fakestoreandro.mapper

import com.example.fakestoreandro.data.model.ProductDto
import com.example.fakestoreandro.model.ProductUIModel

fun ProductDto.toProductUIModel() = ProductUIModel(
    id = id,
    title = title,
    price = price,
    imageUrl = imageUrl,
    rating = rating?.toRatingUIModel()
)

fun List<ProductDto>.toProductUIModel() = map(ProductDto::toProductUIModel)