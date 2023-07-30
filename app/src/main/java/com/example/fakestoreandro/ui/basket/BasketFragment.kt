package com.example.fakestoreandro.ui.basket

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fakestoreandro.R
import com.example.fakestoreandro.databinding.FragmentBasketBinding
import com.example.fakestoreandro.ui.customview.LoadingDialog
import com.example.fakestoreandro.util.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketFragment : Fragment(R.layout.fragment_basket) {
    private val binding by viewBinding(FragmentBasketBinding::bind)

    private val viewModel: BasketViewModel by viewModels()

    private val loadingDialog: LoadingDialog by lazy { LoadingDialog(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            toolbar.setNavigationOnClickListener { findNavController().navigateUp() }

        }
    }
}