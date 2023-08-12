package com.example.fakestoreandro.domain.validation.payment

import com.example.fakestoreandro.domain.validation.ValidateEmptyField

data class PaymentValidations(
    val validateCardNumber: ValidateCardNumber,
    val validateCardHolderName: ValidateEmptyField,
    val validateExpiryDate: ValidateExpiryDate,
    val validateCvc: ValidateCvc
)