package com.example.product.productdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.local.entity.Basket
import com.example.domain.usecase.AddToBasketUseCase
import com.example.ui.UiEvent
import com.example.ui.model.ProductUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val addToBasketUseCase: AddToBasketUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductUIState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    fun addProductToBasket(productUIModel: ProductUIModel) {
        viewModelScope.launch {
            _uiState.update { state -> state.copy(isLoading = true) }

            delay(1000)

            try {
                val basket = Basket(
                    id = 0,
                    productId = productUIModel.id,
                    productTitle = productUIModel.title,
                    productImageUrl = productUIModel.imageUrl,
                    productPrice = productUIModel.price,
                    productQuantity = 1
                )

                addToBasketUseCase(basket)

                _uiEvent.emit(UiEvent.Success(successMessage = "Successfully added!"))

                _uiState.update { state -> state.copy(isLoading = false) }
            } catch (e: Exception) {
                _uiEvent.emit(
                    UiEvent.Error(
                        errorMessage = e.localizedMessage ?: "Failed when add a product to cart!"
                    )
                )

                _uiState.update { state -> state.copy(isLoading = false) }
            }
        }
    }
}

