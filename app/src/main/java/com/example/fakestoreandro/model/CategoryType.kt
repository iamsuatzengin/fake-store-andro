package com.example.fakestoreandro.model

import androidx.annotation.DrawableRes
import com.example.fakestoreandro.R

enum class CategoryType(
    val id: Int,
    val title: String,
    @DrawableRes val image: Int,
) {
    JEWELRY(100, "jewelery", R.drawable.jewelery),
    MENS_CLOTHING(101, "men's clothing", R.drawable.mens_clothing),
    WOMENS_CLOTHING(102, "women's clothing", R.drawable.womens_clothing),
    ELECTRONICS(103, "electronics", R.drawable.electronics);

    fun getCapilatizedTitle() = title.replaceFirstChar { char -> char.uppercaseChar() }
}