package com.example.fakestoreandro.data.repository

import com.example.fakestoreandro.data.local.dao.BasketDao
import com.example.fakestoreandro.data.local.entity.Basket
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BasketRepository @Inject constructor(
    private val basketDao: BasketDao
) {

    fun getAllProduct(): Flow<List<Basket>> = basketDao.getAllProduct().flowOn(Dispatchers.IO)

    suspend fun addProduct(basket: Basket) = withContext(Dispatchers.IO) {
        basketDao.addProduct(basket)
    }

    suspend fun updateQuantity(itemId: Int) = withContext(Dispatchers.IO) {
        basketDao.updateQuantity(itemId)
    }

    suspend fun delete(basket: Basket) = withContext(Dispatchers.IO) {
        basketDao.delete(basket)
    }
}