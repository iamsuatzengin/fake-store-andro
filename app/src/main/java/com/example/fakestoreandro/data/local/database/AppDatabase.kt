package com.example.fakestoreandro.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fakestoreandro.data.local.dao.BasketDao
import com.example.fakestoreandro.data.local.entity.Basket

@Database(entities = [Basket::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun basketDao(): BasketDao
}