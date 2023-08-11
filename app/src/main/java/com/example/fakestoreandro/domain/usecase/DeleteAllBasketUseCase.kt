package com.example.fakestoreandro.domain.usecase

import com.example.fakestoreandro.data.repository.BasketRepository
import javax.inject.Inject

class DeleteAllBasketUseCase @Inject constructor(
    private val repository: BasketRepository
) {

    suspend operator fun invoke() = repository.deleteAll()
}