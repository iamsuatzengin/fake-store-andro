package com.example.fakestoreandro.util

sealed class ApiResponse<out T> {
    data class Success<TYPE>(val body: TYPE) : ApiResponse<TYPE>()

    data class Error(val errorMessage: String) : ApiResponse<Nothing>()
}
