package com.example.fakestoreandro.ui.productdetail

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.fakestoreandro.R
import com.example.fakestoreandro.databinding.FragmentProductDetailBinding
import com.example.fakestoreandro.util.viewbinding.viewBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ProductDetailFragment : Fragment(R.layout.fragment_product_detail) {
    private val binding by viewBinding(FragmentProductDetailBinding::bind)

    private var count: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() = with(binding) {
        lifecycleScope.launch {
            repeat(100) {
                delay(1000)
                count++
                println(count)

                clGroup.isVisible = count % 5 == 0

                clGroup2.isVisible = count % 5 != 0
            }
        }
    }
}