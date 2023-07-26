package com.example.fakestoreandro.ui.productlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fakestoreandro.R
import com.example.fakestoreandro.databinding.FragmentProductListBinding
import com.example.fakestoreandro.model.ProductUIModel
import com.example.fakestoreandro.ui.home.adapter.product.ProductAdapter
import com.example.fakestoreandro.ui.home.adapter.product.ProductAdapterCallback
import com.example.fakestoreandro.util.Resource
import com.example.fakestoreandro.util.extension.collectWithLifecycle
import com.example.fakestoreandro.util.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListFragment : Fragment(R.layout.fragment_product_list), ProductAdapterCallback {
    private val binding by viewBinding(FragmentProductListBinding::bind)

    private val productAdapter: ProductAdapter by lazy {
        ProductAdapter(this)
    }

    private val viewModel by viewModels<ProductListViewModel>()

    private val args by navArgs<ProductListFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        binding.apply {
            toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        }

        args.category?.let { category ->
            println("category!!")
            collectProductsByCategory(category)
        }

    }

    private fun collectProductsByCategory(category: String) {
        viewModel.getProductListByCategory(category)

        viewModel.uiState.collectWithLifecycle(viewLifecycleOwner) { state ->
            when(state) {
                is Resource.Success -> {
                    productAdapter.submitList(state.data)
                }
                is Resource.Error -> println("Error : ${state.errorMessage}")
                Resource.Loading -> println("loading!!!")
            }
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.rvProducts

        recyclerView.layoutManager = GridLayoutManager(
            requireContext(), 2, GridLayoutManager.VERTICAL, false
        )

        productAdapter.submitList(args.productList.toList())
        recyclerView.adapter = productAdapter

    }

    override fun onClickCard(product: ProductUIModel) {
        TODO("Not yet implemented")
    }

    override fun onClickAddToBagButton(product: ProductUIModel) {
        TODO("Not yet implemented")
    }

}