package com.example.fakestoreandro.ui.basket.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.fakestoreandro.data.local.entity.Basket

class BasketAdapter(
    private val callback: BasketAdapterCallback
) : ListAdapter<Basket, BasketViewHolder>(BasketDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        return BasketViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item, callback)
    }
}
