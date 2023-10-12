package com.example.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.local.entity.Basket
import kotlinx.coroutines.flow.Flow

@Dao
interface BasketDao {

    @Query("SELECT * FROM Basket ORDER BY id DESC")
    fun getAllProduct(): Flow<List<Basket>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(basket: Basket)

    @Delete
    suspend fun delete(basket: Basket)

    @Query("UPDATE basket SET product_quantity = product_quantity + 1 WHERE id = :itemId")
    suspend fun updateQuantityIncrease(itemId: Int)

    @Query("UPDATE basket SET product_quantity = product_quantity - 1 WHERE id = :itemId")
    suspend fun updateQuantityDecrease(itemId: Int)

    @Query("DELETE FROM Basket")
    suspend fun deleteAll()
}