package com.example.fakestoreandro.util.extension

import com.example.fakestoreandro.util.ApiResponse
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

inline fun <reified T> safeApiCall(crossinline action: suspend () -> HttpResponse): Flow<ApiResponse<T>> {
    return flow {
        action().apply {
            if (status.isSuccess()) {
                val x = body<T>()
                emit(ApiResponse.Success(x))
            } else {
                emit(ApiResponse.Error("${status.value} - ${status.description}"))
            }
        }
    }
        .catch { cause: Throwable ->
            emit(ApiResponse.Error(cause.localizedMessage ?: "Unexpected error!"))
        }
        .flowOn(Dispatchers.IO)
}
