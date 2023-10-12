package com.example.basket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.basket.databinding.ItemBasketBinding
import com.example.local.entity.Basket
import com.example.util.extension.addPrefix
import com.example.util.extension.loadImage

class BasketViewHolder(
    private val binding: ItemBasketBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Basket, callback: BasketAdapterCallback) {
        binding.apply {
            ivProductImage.loadImage(item.productImageUrl)
            tvProductTitle.text = item.productTitle
            tvProductPrice.text = item.productPrice.toString() addPrefix "$"

            productCountView.setItemCount(quantity = item.productQuantity)

            productCountView.onAddButtonClickListener {
                callback.onAddClickListener(item)
            }

            productCountView.onRemoveButtonClickListener {
                callback.onRemoveClickListener(item)
            }
        }
    }

    companion object {
        fun from(parent: ViewGroup): BasketViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemBasketBinding.inflate(layoutInflater, parent, false)
            return BasketViewHolder(binding)
        }
    }
}