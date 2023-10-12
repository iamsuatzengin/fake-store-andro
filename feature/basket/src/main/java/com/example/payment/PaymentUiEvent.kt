package com.example.payment

sealed interface PaymentUiEvent {
    data object Success: PaymentUiEvent

    data class Error(
        val messageCardNumber: String,
        val messageCardHolderName: String,
        val messageExpiryDate: String,
        val messageCvc: String,
    ): PaymentUiEvent
}