package com.example.fakestoreandro.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestoreandro.data.repository.ProductRepository
import com.example.fakestoreandro.model.ProductUIModel
import com.example.fakestoreandro.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ProductRepository,
) : ViewModel() {

    private val _state = MutableStateFlow<List<ProductUIModel>>(emptyList())
    val state = _state.asStateFlow()

    init {
        getProductList()
        //getProductByID(5)
    }

    private fun getProductList() {
        viewModelScope.launch {
            repository.getProducts().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = result.data ?: emptyList()
                    }

                    is Resource.Error -> {
                        println("ERROR ${result.errorMessage}")
                    }

                    is Resource.Loading -> {
                        println("Loading...")
                    }
                }
            }
        }
    }

    fun getProductByID(id: Int) {
        viewModelScope.launch {
            repository.getProductByID(id).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        println("item: ${result.data}")
                    }

                    is Resource.Error -> {
                        Log.e("ERROR", result.errorMessage)
                    }

                    is Resource.Loading -> {
                        println("Loading...")
                    }
                }
            }
        }
    }
}