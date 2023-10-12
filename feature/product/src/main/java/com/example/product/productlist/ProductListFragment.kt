package com.example.product.productlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.product.R
import com.example.product.databinding.FragmentProductListBinding
import com.example.product.home.adapter.product.ProductAdapter
import com.example.product.home.adapter.product.ProductAdapterCallback
import com.example.ui.customview.LoadingDialog
import com.example.ui.customview.snackbar.Snackbom
import com.example.ui.customview.snackbar.SnackbomType
import com.example.ui.model.ProductUIModel
import com.example.util.Resource
import com.example.util.extension.collectWithLifecycle
import com.example.util.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class ProductListFragment : Fragment(R.layout.fragment_product_list), ProductAdapterCallback {
    private val binding by viewBinding(FragmentProductListBinding::bind)

    private val productAdapter: ProductAdapter by lazy {
        ProductAdapter(this)
    }

    private val viewModel by viewModels<ProductListViewModel>()

    private val args by navArgs<ProductListFragmentArgs>()

    private val loadingDialog: LoadingDialog by lazy {
        LoadingDialog(requireContext())
    }

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
            when (state) {
                is Resource.Success -> {
                    productAdapter.submitList(state.data)
                    loadingDialog.showLoading(false)
                }

                is Resource.Error -> {
                    Snackbom.make(
                        requireView(),
                        "Error: ${state.errorMessage}",
                        SnackbomType.ERROR
                    )
                    loadingDialog.showLoading(false)
                }

                Resource.Loading -> {
                    loadingDialog.showLoading(true)
                }
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
        val action =
            ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment(product)
        findNavController().navigate(action)
    }

    override fun onClickAddToBagButton(product: ProductUIModel) {
        val randomType = SnackbomType.entries[Random.nextInt(0, 3)]
        Snackbom.make(
            view = requireView(),
            text = "Snackbom Message.",
            type = randomType
        ).show()
    }
}