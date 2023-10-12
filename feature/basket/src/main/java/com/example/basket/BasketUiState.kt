package com.example.basket

import com.example.local.entity.Basket


data class BasketUiState(
    val list: List<Basket> = emptyList(),
    val isLoading: Boolean = false,
    val totalPrice: Double = 0.0
)