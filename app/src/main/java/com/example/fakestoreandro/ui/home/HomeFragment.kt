package com.example.fakestoreandro.ui.home

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fakestoreandro.R
import com.example.fakestoreandro.databinding.FragmentHomeBinding
import com.example.fakestoreandro.model.CategoryType
import com.example.fakestoreandro.model.ProductUIModel
import com.example.fakestoreandro.ui.home.adapter.category.CategoryAdapter
import com.example.fakestoreandro.ui.home.adapter.product.ProductAdapter
import com.example.fakestoreandro.ui.home.adapter.product.ProductAdapterCallback
import com.example.fakestoreandro.ui.home.adapter.RecyclerViewItemDecoration
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

    private val categoryAdapter: CategoryAdapter by lazy { CategoryAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupProductRecyclerView()
        setupCategoryRecyclerView()

        viewModel.state.collectWithLifecycle(viewLifecycleOwner) {
            productAdapter.submitList(it)
        }

        binding.apply {
            etSearch.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    println(etSearch.text)
                }
                true
            }
        }
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
        Toast.makeText(requireContext(), "Card tıklandı", Toast.LENGTH_SHORT).show()
        findNavController()
            .navigate(R.id.action_homeFragment_to_productDetailFragment)
    }

    override fun onClickAddToBagButton(product: ProductUIModel) {
        Toast.makeText(requireContext(), "Button tıklandı", Toast.LENGTH_SHORT).show()
        val action = HomeFragmentDirections.actionHomeFragmentToProductBottomSheetFragment(product)
        findNavController().navigate(action)
    }
}

