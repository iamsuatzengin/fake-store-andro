package com.example.product.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.AddToBasketUseCase
import com.example.local.entity.Basket
import com.example.repository.ProductRepository
import com.example.ui.UiEvent
import com.example.ui.model.ProductUIModel
import com.example.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ProductRepository,
    private val addToBasketUseCase: AddToBasketUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        getProductList()
    }

    private fun getProductList() {
        viewModelScope.launch {
            delay(1000)
            repository.getProducts().collect { result ->
                when(result) {
                    is Resource.Success -> {
                        _uiState.emit(HomeUiState(list = result.data ?: emptyList()))
                    }
                    is Resource.Error -> {
                        _uiState.emit(HomeUiState(errorMessage = result.errorMessage))
                    }
                    is Resource.Loading -> {
                        _uiState.emit(HomeUiState(isLoading = true))
                    }
                }
            }
        }
    }

    fun addToBasket(productUIModel: ProductUIModel) {
        viewModelScope.launch {
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
                _uiEvent.emit(UiEvent.Success("Successfully added!"))
            }catch (e: Exception) {
                _uiEvent.emit(
                    UiEvent.Error(
                        errorMessage = e.localizedMessage ?: "Failed when add a product to cart!"
                    )
                )
            }
        }
    }
}

