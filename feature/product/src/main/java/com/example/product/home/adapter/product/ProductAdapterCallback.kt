package com.example.product.home.adapter.product

import com.example.ui.model.ProductUIModel


interface ProductAdapterCallback {
    fun onClickCard(product: ProductUIModel)
    fun onClickAddToBagButton(product: ProductUIModel)
}