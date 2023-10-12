package com.example.domain.usecase


import com.example.repository.BasketRepository
import javax.inject.Inject

class DeleteAllBasketUseCase @Inject constructor(
    private val repository: BasketRepository
) {

    suspend operator fun invoke() = repository.deleteAll()
}