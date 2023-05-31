package com.example.fakestoreandro.util

sealed interface Resource<out T> {
    data class Success<S>(val data: S?) : Resource<S>

    data class Error(val errorMessage: String) : Resource<Nothing>

    object Loading : Resource<Nothing>
}
