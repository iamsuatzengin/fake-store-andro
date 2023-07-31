package com.example.fakestoreandro.ui.basket.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.fakestoreandro.data.local.entity.Basket

class BasketDiffUtil : DiffUtil.ItemCallback<Basket>() {
    override fun areItemsTheSame(oldItem: Basket, newItem: Basket): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Basket, newItem: Basket): Boolean {
        return oldItem == newItem
    }
}
