package com.example.fakestoreandro.ui.home.adapter.category

import androidx.recyclerview.widget.DiffUtil
import com.example.fakestoreandro.model.CategoryType

class CategoryDiffCallback : DiffUtil.ItemCallback<CategoryType>() {
    override fun areItemsTheSame(oldItem: CategoryType, newItem: CategoryType): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CategoryType, newItem: CategoryType): Boolean {
        return oldItem == newItem
    }
}