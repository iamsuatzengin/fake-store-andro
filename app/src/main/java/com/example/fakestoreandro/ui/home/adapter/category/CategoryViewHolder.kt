package com.example.fakestoreandro.ui.home.adapter.category

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.example.fakestoreandro.databinding.ItemCategoryBinding
import com.example.fakestoreandro.model.CategoryType

class CategoryViewHolder(
    private val binding: ItemCategoryBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: CategoryType) {
        binding.apply {
            tvCategoryTitle.text = item.getCapilatizedTitle()
            ivCategoryIcon.setImageResource(item.image)
        }
    }
    
    companion object {
        fun from(context: Context): CategoryViewHolder {
            val layoutInflater = LayoutInflater.from(context)
            val binding = ItemCategoryBinding.inflate(layoutInflater)
            return CategoryViewHolder(binding)
        }
    }
}