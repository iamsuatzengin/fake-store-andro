package com.example.mapper

import com.example.model.RatingDto
import com.example.ui.model.RatingUIModel

fun RatingDto.toRatingUIModel() = RatingUIModel(
    rate = rate,
    count = count
)