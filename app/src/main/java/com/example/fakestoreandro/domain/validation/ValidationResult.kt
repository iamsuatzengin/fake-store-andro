package com.example.fakestoreandro.domain.validation

data class ValidationResult(
    val isSuccessful: Boolean,
    val message: String = "",
)
