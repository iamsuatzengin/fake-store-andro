package com.example.fakestoreandro.ui.productdetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fakestoreandro.R
import com.example.fakestoreandro.databinding.FragmentProductDetailBinding
import com.example.fakestoreandro.domain.model.ProductUIModel
import com.example.fakestoreandro.ui.UiEvent
import com.example.fakestoreandro.ui.customview.LoadingDialog
import com.example.fakestoreandro.ui.customview.snackbar.Snackbom
import com.example.fakestoreandro.ui.customview.snackbar.SnackbomType
import com.example.fakestoreandro.util.extension.addPrefix
import com.example.fakestoreandro.util.extension.addSuffix
import com.example.fakestoreandro.util.extension.collectWithLifecycle
import com.example.fakestoreandro.util.extension.loadImage
import com.example.fakestoreandro.util.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductDetailFragment : Fragment(R.layout.fragment_product_detail) {
    private val binding by viewBinding(FragmentProductDetailBinding::bind)

    private val args by navArgs<ProductDetailFragmentArgs>()

    private val viewModel: ProductDetailViewModel by viewModels()

    private val loadingDialog: LoadingDialog by lazy { LoadingDialog(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(args.product)
        collectData()
    }

    private fun initView(product: ProductUIModel) = with(binding) {
        toolbar.setNavigationOnClickListener { findNavController().navigateUp() }

        ivProductImage.loadImage(product.imageUrl)
        tvProductCategory.text = product.category
        tvProductTitle.text = product.title
        tvProductRating.text = product.rating?.rate.toString()
        tvRatingCount.text = product.rating?.count.toString() addPrefix "(" addSuffix ")"
        tvProductPrice.text = product.price.toString() addPrefix "$"

        btnAddToBag.setOnClickListener { viewModel.addProductToBasket(product) }
    }

    private fun collectData() {
        viewLifecycleOwner.collectWithLifecycle(Lifecycle.State.STARTED) {
            launch {
                viewModel.uiState.collectLatest { state ->
                    loadingDialog.showLoading(state.isLoading)
                }
            }

            launch {
                viewModel.uiEvent.collectLatest { event ->
                    when (event) {
                        is UiEvent.Success -> Snackbom.make(
                            requireView(),
                            event.successMessage,
                            SnackbomType.SUCCESS
                        ).show()


                        is UiEvent.Error -> Snackbom.make(
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