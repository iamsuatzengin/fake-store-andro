package com.example.product.home

import com.example.ui.model.ProductUIModel

data class HomeUiState(
    val list: List<ProductUIModel> = emptyList(),
    val errorMessage: String? = null,
    val isLoading: Boolean = false,
)