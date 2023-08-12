package com.example.fakestoreandro.domain.validation.payment

import com.example.fakestoreandro.domain.validation.ValidationResult

class ValidateCardNumber {
    operator fun invoke(cardNumber: String): ValidationResult {
        if (cardNumber.isBlank()) {
            return ValidationResult(
                isSuccessful = false,
                message = "Card number shouldn't be empty!"
            )
        } else if(cardNumber.length < 19){
            return ValidationResult(
                isSuccessful = false,
                message = "Card number should be 19 characters."
            )
        } else if (!cardNumber.contains(' ')) {
            return ValidationResult(isSuccessful = false, message = "Invalid card number!")
        }

        cardNumber.forEach { char ->
            if ((char != ' ') && !char.isDigit()) {
                return ValidationResult(isSuccessful = false, message = "Invalid card number!")
            }
        }

        return ValidationResult(isSuccessful = true)
    }
}