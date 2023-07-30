package com.example.fakestoreandro.ui.home.adapter.product

import com.example.fakestoreandro.domain.model.ProductUIModel

interface ProductAdapterCallback {
    fun onClickCard(product: ProductUIModel)
    fun onClickAddToBagButton(product: ProductUIModel)
}