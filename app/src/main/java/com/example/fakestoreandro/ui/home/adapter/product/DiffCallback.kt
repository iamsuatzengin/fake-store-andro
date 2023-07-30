package com.example.fakestoreandro.ui.home.adapter.product

import androidx.recyclerview.widget.DiffUtil
import com.example.fakestoreandro.domain.model.ProductUIModel

class DiffCallback : DiffUtil.ItemCallback<ProductUIModel>() {
    override fun areItemsTheSame(oldItem: ProductUIModel, newItem: ProductUIModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ProductUIModel, newItem: ProductUIModel): Boolean {
        return oldItem == newItem
    }
}