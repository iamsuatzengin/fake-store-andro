package com.example.util.extension


import com.example.util.ApiResponse
import com.example.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

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
        .onStart { emit(Resource.Loading) }
        .flowOn(Dispatchers.IO)
}