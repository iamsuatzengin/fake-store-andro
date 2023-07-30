package com.example.fakestoreandro.ui.basket

import androidx.lifecycle.ViewModel
import com.example.fakestoreandro.data.repository.BasketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val basketRepository: BasketRepository
) : ViewModel() {

}