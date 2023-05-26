package com.example.fakestoreandro.data.service

import com.example.fakestoreandro.core.safeApiCall
import com.example.fakestoreandro.data.model.ProductDto
import com.example.fakestoreandro.data.model.ProductRequest
import com.example.fakestoreandro.util.ApiResponse
import com.example.fakestoreandro.util.Constants
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ApiService @Inject constructor(
    private val client: HttpClient
) {
    suspend fun getProductList(): Flow<ApiResponse<List<ProductDto>>> = safeApiCall {
        client.get(Constants.PRODUCTS)
    }

    suspend fun getProductByID(id: Int = 1): Flow<ApiResponse<ProductDto>> = safeApiCall {
        client.get("${Constants.PRODUCTS}/$id")
    }

    suspend fun addNewProduct(): Flow<ApiResponse<ProductDto>> = safeApiCall {
        val newProduct = ProductRequest(
            "yeni ürünüm",
            24.5,
            "açıkllama yeni ürünün",
            "imageUrl",
            "kategori"
        )
        client.post(Constants.PRODUCTS) {
            contentType(ContentType.Application.Json)
            setBody(newProduct)
        }
    }
}

