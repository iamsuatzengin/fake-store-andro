package com.example.fakestoreandro.ui.basket

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fakestoreandro.R
import com.example.fakestoreandro.data.local.entity.Basket
import com.example.fakestoreandro.databinding.FragmentBasketBinding
import com.example.fakestoreandro.ui.UiEvent
import com.example.fakestoreandro.ui.basket.adapter.BasketAdapter
import com.example.fakestoreandro.ui.basket.adapter.BasketAdapterCallback
import com.example.fakestoreandro.ui.customview.LoadingDialog
import com.example.fakestoreandro.ui.customview.snackbar.Snackbom
import com.example.fakestoreandro.ui.customview.snackbar.SnackbomType
import com.example.fakestoreandro.util.extension.addPrefix
import com.example.fakestoreandro.util.extension.collectWithLifecycle
import com.example.fakestoreandro.util.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BasketFragment : Fragment(R.layout.fragment_basket), BasketAdapterCallback {
    private val binding by viewBinding(FragmentBasketBinding::bind)

    private val viewModel: BasketViewModel by viewModels()

    private val loadingDialog: LoadingDialog by lazy { LoadingDialog(requireContext()) }

    private val basketAdapter: BasketAdapter by lazy {
        BasketAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewLifecycleOwner.collectWithLifecycle(Lifecycle.State.STARTED) {
            launch {
                viewModel.uiState.collect { state ->
                    initView(state)
                }
            }

            launch {
                viewModel.uiEvent.collectLatest { event ->
                    when (event) {
                        is UiEvent.Success -> {}
                        is UiEvent.Error -> {
                            Snackbom.make(
                                requireView(),
                                event.errorMessage,
                                SnackbomType.ERROR
                            ).show()
                        }
                    }
                }
            }
        }
    }

    private fun initView(uiState: BasketUiState) {
        loadingDialog.showLoading(uiState.isLoading)
        basketAdapter.submitList(uiState.list)
        viewModel.calculateTotalPrice(uiState.list)

        binding.apply {
            val selected = resources.getString(R.string.text_selected_products, uiState.list.size)
            tvSelectedProducts.text = selected
            tvTotalPrice.text = uiState.totalPrice.toString() addPrefix "$"
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.rvBasket
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        recyclerView.adapter = basketAdapter
    }

    override fun onAddClickListener(basket: Basket) {
        viewModel.updateQuantityIncrease(basket.id)
    }

    override fun onRemoveClickListener(basket: Basket) {
        if (basket.productQuantity == 1) {
            viewModel.deleteProductFromBasket(basket)
            return
        }
        viewModel.updateQuantityDecrease(basket.id)
    }
}