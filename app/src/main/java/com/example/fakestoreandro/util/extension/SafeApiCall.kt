package com.example.fakestoreandro.util.extension

import com.example.fakestoreandro.util.ApiResponse
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

inline fun <reified T> safeApiCall(crossinline action: suspend () -> HttpResponse): Flow<ApiResponse<T>> {
    return flow {
        try {
            action().apply {
                if (status.isSuccess()) {
                    val x = body<T>()
                    emit(ApiResponse.Success(x))
                } else {
                    emit(ApiResponse.Error("${status.value} - ${status.description}"))
                }
            }
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.message ?: "Unexpected error!"))
        }
    }.flowOn(Dispatchers.IO)
}
