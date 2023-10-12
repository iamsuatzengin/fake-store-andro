package com.example.domain.validation.payment

import com.example.domain.validation.ValidationResult

class ValidateExpiryDate {
    operator fun invoke(expiryDate: String): ValidationResult {
        if (expiryDate.isBlank()) {
            return ValidationResult(
                isSuccessful = false,
                message = "Expiry date shouldn't be empty!"
            )
        } else if (expiryDate.length < 5 || expiryDate.contains(' ')) {
            return ValidationResult(isSuccessful = false, message = "Invalid expiry date!")
        }

        expiryDate.forEach { char ->
            if (char != '/' && !char.isDigit()) {
                return ValidationResult(isSuccessful = false, message = "Invalid expiry date!")
            }
        }

        return ValidationResult(isSuccessful = true)
    }
}