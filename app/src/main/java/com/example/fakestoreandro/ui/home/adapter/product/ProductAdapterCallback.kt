package com.example.fakestoreandro.ui.home.adapter.product

import com.example.fakestoreandro.model.ProductUIModel

interface ProductAdapterCallback {
    fun onClickCard(product: ProductUIModel)
    fun onClickAddToBagButton(product: ProductUIModel)
}