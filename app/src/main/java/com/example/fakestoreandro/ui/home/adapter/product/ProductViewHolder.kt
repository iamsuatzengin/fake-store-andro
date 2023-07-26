package com.example.fakestoreandro.ui.home.adapter.product

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.example.fakestoreandro.databinding.ItemProductBinding
import com.example.fakestoreandro.model.ProductUIModel
import com.example.fakestoreandro.util.extension.addPrefix
import com.example.fakestoreandro.util.extension.loadImage

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