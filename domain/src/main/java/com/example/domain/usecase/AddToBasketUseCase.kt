package com.example.domain.usecase

import com.example.local.entity.Basket
import com.example.repository.BasketRepository
import javax.inject.Inject


class AddToBasketUseCase @Inject constructor(
    private val basketRepository: BasketRepository
) {
    suspend operator fun invoke(basket: Basket) = basketRepository.addProduct(basket)
}