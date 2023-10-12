package com.example.product.home.adapter.category

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.ui.model.CategoryType

class CategoryAdapter(
    private val onCategoryClick: (CategoryType) -> Unit
) : ListAdapter<CategoryType, CategoryViewHolder>(CategoryDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder.from(parent.context)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item, onCategoryClick)
    }
}


