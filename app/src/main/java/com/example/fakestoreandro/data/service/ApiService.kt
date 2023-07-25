package com.example.fakestoreandro.data.service

import com.example.fakestoreandro.data.model.ProductDto
import com.example.fakestoreandro.data.model.ProductRequestModel
import com.example.fakestoreandro.util.ApiResponse
import com.example.fakestoreandro.util.Constants
import com.example.fakestoreandro.util.extension.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.appendPathSegments
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ApiService @Inject constructor(
    private val client: HttpClient
) {
    suspend fun getProductList(): Flow<ApiResponse<List<ProductDto>>> = safeApiCall {
        client.get(Constants.PRODUCTS)
    }

    suspend fun getProductByID(id: Int = 5): Flow<ApiResponse<ProductDto>> = safeApiCall {
        client.get{
            url(path = Constants.PRODUCTS){
                appendPathSegments("$id")
            }
        }
    }

    suspend fun addNewProduct(): Flow<ApiResponse<ProductDto>> = safeApiCall {
        val newProduct = ProductRequestModel(
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

    suspend fun updateProduct(id: Int): Flow<ApiResponse<ProductDto>> = safeApiCall {
        val updatedProduct = ProductRequestModel(
            "guncelgregergregre title",
            355.5,
            "güncelleme",
            "url image",
            "kategorixxxxxxxx"
        )
        client.put("${Constants.PRODUCTS}/dd$id") {
            contentType(ContentType.Application.Json)
            setBody(updatedProduct)
        }
    }

    suspend fun create(): ApiResponse<ProductDto> {
        val newProduct = ProductRequestModel(
            "yeni ürünüm",
            24.5,
            "açıkllama yeni ürünün",
            "imageUrl",
            "kategori"
        )
        val request = client.post(Constants.PRODUCTS) {
            contentType(ContentType.Application.Json)
            setBody(newProduct)
        }
        if(!request.status.isSuccess()){
            return ApiResponse.Error(request.status.description)
        }

        return ApiResponse.Success(request.body())
    }

    suspend fun update(id: Int): ApiResponse<ProductDto> {
        val updatedProduct = ProductRequestModel(
            "guncelgregergregre title",
            355.5,
            "güncelleme",
            "url image",
            "kategorixxxxxxxx"
        )
        val response = client.put("${Constants.PRODUCTS}/dd$id") {
            contentType(ContentType.Application.Json)
            setBody(updatedProduct)
        }
        if(!response.status.isSuccess()){
            return ApiResponse.Error(response.status.description)
        }

        return ApiResponse.Success(response.body())
    }
}