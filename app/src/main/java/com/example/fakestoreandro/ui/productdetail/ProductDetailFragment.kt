package com.example.fakestoreandro.ui.productdetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fakestoreandro.R
import com.example.fakestoreandro.databinding.FragmentProductDetailBinding
import com.example.fakestoreandro.model.ProductUIModel
import com.example.fakestoreandro.util.extension.addPrefix
import com.example.fakestoreandro.util.extension.addSuffix
import com.example.fakestoreandro.util.extension.loadImage
import com.example.fakestoreandro.util.viewbinding.viewBinding


class ProductDetailFragment : Fragment(R.layout.fragment_product_detail) {
    private val binding by viewBinding(FragmentProductDetailBinding::bind)

    private val args by navArgs<ProductDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(args.product)
    }

    private fun initView(product: ProductUIModel) = with(binding) {
        toolbar.setNavigationOnClickListener { findNavController().navigateUp() }

        println("args: ${args.product.title}")

        ivProductImage.loadImage(product.imageUrl)
        tvProductCategory.text = product.category
        tvProductTitle.text = product.title
        tvProductRating.text = product.rating?.rate.toString()
        tvRatingCount.text = product.rating?.count.toString() addPrefix "(" addSuffix ")"
        tvProductPrice.text = product.price.toString() addPrefix "$"
    }
}