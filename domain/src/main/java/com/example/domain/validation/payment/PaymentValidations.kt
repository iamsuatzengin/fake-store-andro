package com.example.domain.validation.payment

import com.example.domain.validation.ValidateEmptyField

data class PaymentValidations(
    val validateCardNumber: ValidateCardNumber,
    val validateCardHolderName: ValidateEmptyField,
    val validateExpiryDate: ValidateExpiryDate,
    val validateCvc: ValidateCvc
)