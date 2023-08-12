package com.example.fakestoreandro.domain.validation.payment

import com.example.fakestoreandro.domain.validation.ValidationResult

class ValidateCvc {
    operator fun invoke(cvcNumber: String): ValidationResult {
        return if (cvcNumber.isBlank()) {
            ValidationResult(isSuccessful = false, message = "CVC shouldn't be empty!")
        } else if (cvcNumber.length < 3) {
            ValidationResult(
                isSuccessful = false,
                message = "CVC shouldn't be lower than 3!"
            )
        } else {
            ValidationResult(isSuccessful = true)
        }
    }
}