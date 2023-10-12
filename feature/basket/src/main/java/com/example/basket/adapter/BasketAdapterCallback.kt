package com.example.basket.adapter

import com.example.local.entity.Basket


interface BasketAdapterCallback {

    fun onAddClickListener(basket: Basket)
    fun onRemoveClickListener(basket: Basket)
}