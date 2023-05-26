package com.example.fakestoreandro.mapper

import com.example.fakestoreandro.data.model.ProductDto
import com.example.fakestoreandro.data.model.RatingDto
import com.example.fakestoreandro.model.ProductUIModel
import com.example.fakestoreandro.model.RatingUIModel


fun ProductDto.toProductUIModel() = ProductUIModel(
    id = id,
    title = title,
    price = price,
    imageUrl = imageUrl,
    rating = rating?.toRatingUIModel()
)

fun RatingDto.toRatingUIModel() = RatingUIModel(
    rate = rate,
    count = count
)