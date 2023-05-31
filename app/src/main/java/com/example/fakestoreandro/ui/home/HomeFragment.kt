package com.example.fakestoreandro.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import com.example.fakestoreandro.R
import com.example.fakestoreandro.databinding.FragmentHomeBinding
import com.example.fakestoreandro.util.extension.observeFlowWithLifecycle
import com.example.fakestoreandro.util.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tv.text = "Loading"

        binding.btnAddNewProduct.setOnClickListener {
            viewModel.addNewProduct()
        }

        //viewModel.getProductByID(1)

        observeFlowWithLifecycle(Lifecycle.State.STARTED) {
            viewModel.state.collect {
                if (it.isNotEmpty()) {
                    println(
                        """
                        list item first: 
                        ${it.first()}
                    """.trimIndent()
                    )

                    binding.tv.text = it.first().title
                }
            }
        }
    }
}

