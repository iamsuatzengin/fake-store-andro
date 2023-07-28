package com.example.fakestoreandro.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestoreandro.data.repository.ProductRepository
import com.example.fakestoreandro.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ProductRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState = _uiState.asStateFlow()

    init {
        getProductList()
    }

    private fun getProductList() {
        viewModelScope.launch {
            delay(1000)
            repository.getProducts().collect { result ->
                when(result) {
                    is Resource.Success -> {
                        _uiState.emit(HomeUiState(list = result.data ?: emptyList()))
                    }
                    is Resource.Error -> {
                        _uiState.emit(HomeUiState(errorMessage = result.errorMessage))
                    }
                    is Resource.Loading -> {
                        _uiState.emit(HomeUiState(isLoading = true))
                    }
                }
            }
        }
    }

    // now, this function is never used!
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

