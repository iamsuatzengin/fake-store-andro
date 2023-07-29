package com.example.fakestoreandro.ui.home

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fakestoreandro.R
import com.example.fakestoreandro.databinding.FragmentHomeBinding
import com.example.fakestoreandro.model.CategoryType
import com.example.fakestoreandro.model.ProductUIModel
import com.example.fakestoreandro.ui.customview.LoadingDialog
import com.example.fakestoreandro.ui.home.adapter.RecyclerViewItemDecoration
import com.example.fakestoreandro.ui.home.adapter.category.CategoryAdapter
import com.example.fakestoreandro.ui.home.adapter.product.ProductAdapter
import com.example.fakestoreandro.ui.home.adapter.product.ProductAdapterCallback
import com.example.fakestoreandro.util.extension.collectWithLifecycle
import com.example.fakestoreandro.util.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), ProductAdapterCallback {
    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels()

    private val productAdapter: ProductAdapter by lazy {
        ProductAdapter(this)
    }

    private val categoryAdapter: CategoryAdapter by lazy { CategoryAdapter(this::onCategoryClick) }

    private val loadingDialog: LoadingDialog by lazy {
        LoadingDialog(requireContext())
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupProductRecyclerView()
        setupCategoryRecyclerView()

        viewLifecycleOwner.collectWithLifecycle(Lifecycle.State.STARTED) {
            viewModel.uiState.collect { state ->
                if(state.isLoading) binding.clGroup.isVisible = false

                loadingDialog.showLoading(state.isLoading)

                println("state is Loading: ${state.isLoading}")

                if (!state.errorMessage.isNullOrEmpty()) println("error : ${state.errorMessage}")

                if (state.list.isNotEmpty()) {
                    binding.clGroup.isVisible = true
                    productAdapter.submitList(state.list.subList(0, 3))
                }
            }
        }

        binding.apply {
            etSearch.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    println(etSearch.text)
                }
                true
            }

            tvSeeAll.setOnClickListener {

                val productList = viewModel.uiState.value.list
                val action =
                    HomeFragmentDirections.actionHomeFragmentToProductListFragment(
                        productList.toTypedArray(), null
                    )
                findNavController().navigate(action)

            }
        }
    }

    private fun onCategoryClick(categoryType: CategoryType) {
        val action = HomeFragmentDirections.actionHomeFragmentToProductListFragment(
            emptyArray(), category = categoryType.title
        )
        findNavController().navigate(action)
    }

    private fun setupProductRecyclerView() {
        val recyclerView = binding.rvProductList
        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.HORIZONTAL, false
        )
        recyclerView.addItemDecoration(RecyclerViewItemDecoration())
        recyclerView.adapter = productAdapter
    }

    private fun setupCategoryRecyclerView() {
        val categoryRecyclerView = binding.rvCategory
        categoryRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.HORIZONTAL, false
        )
        categoryRecyclerView.addItemDecoration(RecyclerViewItemDecoration())

        categoryAdapter.submitList(CategoryType.values().toList())
        categoryRecyclerView.adapter = categoryAdapter
    }

    override fun onClickCard(product: ProductUIModel) {
        val action = HomeFragmentDirections.actionHomeFragmentToProductDetailFragment(product)
        findNavController().navigate(action)
    }

    override fun onClickAddToBagButton(product: ProductUIModel) {
        val action = HomeFragmentDirections.actionHomeFragmentToProductBottomSheetFragment(product)
        findNavController().navigate(action)
    }
}

