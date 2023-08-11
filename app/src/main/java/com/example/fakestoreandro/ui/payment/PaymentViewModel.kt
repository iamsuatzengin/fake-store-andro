package com.example.fakestoreandro.ui.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestoreandro.domain.usecase.DeleteAllBasketUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val deleteAllBasketUseCase: DeleteAllBasketUseCase
) : ViewModel(){

    fun deleteAll() {
        viewModelScope.launch {
            deleteAllBasketUseCase()
        }
    }
}