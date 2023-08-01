package com.example.fakestoreandro.ui.basket

import com.example.fakestoreandro.data.local.entity.Basket

data class BasketUiState(
    val list: List<Basket> = emptyList(),
    val isLoading: Boolean = false,
    val totalPrice: Double = 0.0
)