package com.example.fakestoreandro.ui.basket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestoreandro.data.local.entity.Basket
import com.example.fakestoreandro.data.repository.BasketRepository
import com.example.fakestoreandro.ui.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val basketRepository: BasketRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(BasketUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        getAllProductFromBasket()
    }

    private fun getAllProductFromBasket() {
        viewModelScope.launch {
            _uiState.update { state -> state.copy(isLoading = true) }

            basketRepository.getAllProduct().collect { list ->
                _uiState.update { state -> state.copy(list = list, isLoading = false) }
            }
        }
    }

    fun updateQuantityIncrease(itemId: Int) {
        viewModelScope.launch {
            _uiState.update { state -> state.copy(isLoading = true) }
            delay(1000)

            try {
                basketRepository.updateQuantityIncrease(itemId)

                _uiEvent.emit(UiEvent.Success())
                _uiState.update { state -> state.copy(isLoading = false) }
            } catch (e: Exception) {
                _uiEvent.emit(
                    UiEvent.Error(
                        errorMessage = e.localizedMessage ?: "Unexpected error!"
                    )
                )
                _uiState.update { state -> state.copy(isLoading = false) }
            }
        }
    }

    fun updateQuantityDecrease(itemId: Int) {
        viewModelScope.launch {
            _uiState.update { state -> state.copy(isLoading = true) }
            delay(1000)

            try {
                basketRepository.updateQuantityDecrease(itemId)

                _uiEvent.emit(UiEvent.Success())
                _uiState.update { state -> state.copy(isLoading = false) }
            } catch (e: Exception) {
                _uiEvent.emit(
                    UiEvent.Error(
                        errorMessage = e.localizedMessage ?: "Unexpected error!"
                    )
                )
                _uiState.update { state -> state.copy(isLoading = false) }
            }
        }
    }

    fun calculateTotalPrice(list: List<Basket>) {
        var totalPrice = 0.0

        list.map { item ->
            totalPrice += item.productPrice * item.productQuantity
        }

        _uiState.update { state -> state.copy(totalPrice = totalPrice.roundToInt().toDouble()) }
    }

    fun deleteProductFromBasket(basket: Basket) {
        viewModelScope.launch {
            _uiState.update { state -> state.copy(isLoading = true) }
            delay(1000)

            try {
                basketRepository.deleteProductFromBasket(basket)

                _uiEvent.emit(UiEvent.Success())
                _uiState.update { state -> state.copy(isLoading = false) }
            } catch (e: Exception) {
                _uiEvent.emit(
                    UiEvent.Error(
                        errorMessage = e.localizedMessage ?: "Unexpected error!"
                    )
                )
                _uiState.update { state -> state.copy(isLoading = false) }
            }
        }
    }
}
