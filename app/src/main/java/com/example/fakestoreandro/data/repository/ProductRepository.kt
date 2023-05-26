package com.example.fakestoreandro.data.repository

import com.example.fakestoreandro.data.service.ApiService
import com.example.fakestoreandro.mapper.toProductUIModel
import com.example.fakestoreandro.model.ProductUIModel
import com.example.fakestoreandro.util.ApiResponse
import com.example.fakestoreandro.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val apiService: ApiService
) {
    fun getProductList(): Flow<Resource<List<ProductUIModel>>> = flow {
        emit(Resource.Loading)
        apiService.getProductList().collect {
            when (it) {
                is ApiResponse.Success -> {
                    emit(
                        Resource.Success(it.body.map { productDto ->
                            productDto.toProductUIModel()
                        })
                    )
                }

                is ApiResponse.Error -> {
                    emit(Resource.Error(it.errorMessage))
                }
            }
        }
    }.flowOn(Dispatchers.IO)

    fun getProductByID(id: Int): Flow<Resource<ProductUIModel>> = flow {
        emit(Resource.Loading)
        apiService.getProductByID(id = id).collect {
            when (it) {
                is ApiResponse.Success -> {
                    emit(Resource.Success(it.body.toProductUIModel()))
                }

                is ApiResponse.Error -> {
                    emit(Resource.Error(it.errorMessage))
                }
            }
        }
    }.flowOn(Dispatchers.IO)

    fun addNewProduct(): Flow<Resource<ProductUIModel>> = flow {
        emit(Resource.Loading)
        apiService.addNewProduct().collect {
            when (it) {
                is ApiResponse.Success -> {
                    emit(Resource.Success(it.body.toProductUIModel()))
                }

                is ApiResponse.Error -> {
                    emit(Resource.Error(it.errorMessage))
                }
            }
        }
    }.flowOn(Dispatchers.IO)
}