package com.example.fakestoreandro.ui.home.bottomsheet

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.fakestoreandro.R
import com.example.fakestoreandro.databinding.FragmentProductBottomSheetBinding
import com.example.fakestoreandro.util.extension.addPrefix
import com.example.fakestoreandro.util.extension.addSuffix
import com.example.fakestoreandro.util.extension.loadImage
import com.example.fakestoreandro.util.viewbinding.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ProductBottomSheetFragment :
    BottomSheetDialogFragment(R.layout.fragment_product_bottom_sheet) {

    private val binding by viewBinding(FragmentProductBottomSheetBinding::bind)

    private val args: ProductBottomSheetFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val product = args.product

        binding.apply {
            ivProductImage.loadImage(product.imageUrl)
            tvProductTitle.text = product.title
            tvProductPrice.text = product.price.toString() addPrefix "$"
            tvCategoryTitle.text = product.category
            tvRating.text = product.rating?.rate.toString()
            tvRatingCount.text = product.rating?.count.toString() addPrefix "(" addSuffix  ")"
        }

        binding.viewProductCount.setCountListener {
            println("listener -- count : ${it} ")
        }
    }
}