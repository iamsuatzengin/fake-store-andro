package com.example.fakestoreandro.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RatingDto(
    @SerialName("rate")
    val rate: Double? = null,
    @SerialName("count")
    val count: Int? = null,
)
