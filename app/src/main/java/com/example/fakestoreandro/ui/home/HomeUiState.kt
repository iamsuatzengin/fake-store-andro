package com.example.fakestoreandro.ui.home

import com.example.fakestoreandro.domain.model.ProductUIModel

data class HomeUiState(
    val list: List<ProductUIModel> = emptyList(),
    val errorMessage: String? = null,
    val isLoading: Boolean = false,
)