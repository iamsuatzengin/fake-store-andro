package com.example.product.home.adapter.product

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.ui.model.ProductUIModel


class ProductAdapter(
    private val productAdapterCallback: ProductAdapterCallback
) : ListAdapter<ProductUIModel, ProductViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder.from(parent.context)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, productAdapterCallback)
    }
}
