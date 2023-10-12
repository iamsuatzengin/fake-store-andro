package com.example.product.home.adapter.product

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.example.product.databinding.ItemProductBinding
import com.example.ui.model.ProductUIModel
import com.example.util.extension.addPrefix
import com.example.util.extension.loadImage

class ProductViewHolder(
    private val binding: ItemProductBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ProductUIModel, productAdapterCallback: ProductAdapterCallback) {
        binding.apply {
            ivProductImage.loadImage(item.imageUrl)

            tvProductTitle.text = item.title
            tvProductPrice.text = item.price.toString() addPrefix "$"

            cardViewContainer.setOnClickListener {
                productAdapterCallback.onClickCard(item)
            }

            btnAddToBag.setOnClickListener {
                productAdapterCallback.onClickAddToBagButton(item)
            }
        }
    }


    companion object {
        fun from(context: Context): ProductViewHolder {
            val layoutInflater = LayoutInflater.from(context)
            val binding = ItemProductBinding.inflate(layoutInflater)
            return ProductViewHolder(binding)
        }
    }
}