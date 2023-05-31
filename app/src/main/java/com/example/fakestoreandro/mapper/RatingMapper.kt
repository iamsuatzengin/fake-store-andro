package com.example.fakestoreandro.mapper

import com.example.fakestoreandro.data.model.RatingDto
import com.example.fakestoreandro.model.RatingUIModel

fun RatingDto.toRatingUIModel() = RatingUIModel(
    rate = rate,
    count = count
)