package com.example.fakestoreandro.domain.validation

class ValidateEmptyField {
    operator fun invoke(field: String?) : ValidationResult {
        return if(field.isNullOrEmpty()) {
            ValidationResult(isSuccessful = false, message = "It can't be empty!")
        } else {
            ValidationResult(isSuccessful = true)
        }
    }
}