package com.example.fakestoreandro.ui.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestoreandro.domain.usecase.DeleteAllBasketUseCase
import com.example.fakestoreandro.domain.validation.payment.PaymentValidations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val deleteAllBasketUseCase: DeleteAllBasketUseCase,
    private val paymentValidations: PaymentValidations
) : ViewModel() {

    private val _uiEvent = MutableSharedFlow<PaymentUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    fun validation(
        cardNumber: String,
        cardHolderName: String,
        expiryDate: String,
        cvcNumber: String
    ) {
        viewModelScope.launch {
            val cardNumberResult = paymentValidations.validateCardNumber(cardNumber)
            val cardHolderNameResult = paymentValidations.validateCardHolderName(cardHolderName)
            val expiryDateResult = paymentValidations.validateExpiryDate(expiryDate)
            val cvcResult = paymentValidations.validateCvc(cvcNumber)

            val hasError = listOf(
                cardNumberResult, cardHolderNameResult, expiryDateResult, cvcResult
            ).any { !it.isSuccessful }

            if (hasError) {
                _uiEvent.emit(
                    PaymentUiEvent.Error(
                        messageCardNumber = cardNumberResult.message,
                        messageCardHolderName = cardHolderNameResult.message,
                        messageExpiryDate = expiryDateResult.message,
                        messageCvc = cvcResult.message
                    )
                )
                return@launch
            }
            _uiEvent.emit(PaymentUiEvent.Success)
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            deleteAllBasketUseCase()
        }
    }
}