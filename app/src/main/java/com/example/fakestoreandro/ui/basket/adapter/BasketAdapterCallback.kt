package com.example.fakestoreandro.ui.basket.adapter

import com.example.fakestoreandro.data.local.entity.Basket

interface BasketAdapterCallback {

    fun onAddClickListener(basket: Basket)
    fun onRemoveClickListener(basket: Basket)
}