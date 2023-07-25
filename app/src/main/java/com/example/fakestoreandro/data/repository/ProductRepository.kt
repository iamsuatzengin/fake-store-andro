package com.example.fakestoreandro.data.repository

import com.example.fakestoreandro.data.service.ApiService
import com.example.fakestoreandro.mapper.toProductUIModel
import com.example.fakestoreandro.model.ProductUIModel
import com.example.fakestoreandro.util.ApiResponse
import com.example.fakestoreandro.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

inline fun <T, R> Flow<ApiResponse<T>>.mapOnSuccess(
    crossinline action: (T) -> R
): Flow<Resource<R>> {
    return this.map {
        when (it) {
            is ApiResponse.Success -> {
                Resource.Success(action(it.body))
            }

            is ApiResponse.Error -> Resource.Error(it.errorMessage)
        }
    }
}

class ProductRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getProducts(): Flow<Resource<List<ProductUIModel>>> {
        return apiService.getProductList().mapOnSuccess {
            it.toProductUIModel()
        }
    }

    suspend fun getProductList(): Flow<Resource<List<ProductUIModel>>> = flow {
        emit(Resource.Loading)
        apiService.getProductList().collect {
            when (it) {
                is ApiResponse.Success -> {
                    emit(
                        Resource.Success(it.body.toProductUIModel())
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

    suspend fun addNewProduct(): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading)
        apiService.addNewProduct().collect {
            when (it) {
                is ApiResponse.Success -> {
                    updateProduct(it.body.id!!).map {res ->
                        emit(res)
                    }.collect()
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(it.errorMessage))
                }
            }
        }
    }.flowOn(Dispatchers.IO)

    private suspend fun updateProduct(id: Int): Flow<Resource<Unit>> = flow {
        apiService.updateProduct(id).collect {
            when (it) {
                is ApiResponse.Success -> {
                    println("başarılı şekilde güncellendi\n${it.body}")
                    emit(Resource.Success(Unit))
                }

                is ApiResponse.Error -> {
                    println("hataaaaaaaaaaaaaaaaaaaaaa \n${it}")
                    emit(Resource.Error(it.errorMessage))
                }
            }
        }
    }

    suspend fun create() : Flow<Resource<Unit>> = flow{
        when(val a = apiService.create()){
            is ApiResponse.Success -> {
                val b = apiService.update(a.body.id!!)
                emit(Resource.Success(Unit))
            }
            is ApiResponse.Error -> {
                emit(Resource.Error(a.errorMessage))
            }
        }
    }

}
