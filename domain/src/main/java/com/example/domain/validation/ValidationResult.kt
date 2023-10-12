package com.example.domain.validation

data class ValidationResult(
    val isSuccessful: Boolean,
    val message: String = "",
)
