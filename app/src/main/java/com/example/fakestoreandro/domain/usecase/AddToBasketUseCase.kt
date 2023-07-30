package com.example.fakestoreandro.domain.usecase

import com.example.fakestoreandro.data.local.entity.Basket
import com.example.fakestoreandro.data.repository.BasketRepository
import javax.inject.Inject

class AddToBasketUseCase @Inject constructor(
    private val basketRepository: BasketRepository
) {
    suspend operator fun invoke(basket: Basket) = basketRepository.addProduct(basket)
}