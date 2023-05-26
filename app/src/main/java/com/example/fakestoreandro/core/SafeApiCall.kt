package com.example.fakestoreandro.core

import com.example.fakestoreandro.util.ApiResponse
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.SerializationException


inline fun <reified T> safeApiCall(crossinline action: suspend () -> HttpResponse): Flow<ApiResponse<T>> {
    return flow {
        try {
            action().apply {
                if (status.value in 200..299) {
                    emit(ApiResponse.Success(body()))
                }
            }
        } catch (e: ServerResponseException) {
            emit(ApiResponse.Error("response : ${e.response.status}"))
        } catch (e: ClientRequestException) {
            emit(ApiResponse.Error("client req : ${e.response.status.description}"))
        } catch (e: IOException) {
            emit(ApiResponse.Error("io ex : ${e.localizedMessage}"))
        } catch (e: SerializationException) {
            emit(ApiResponse.Error("seri exc : ${e.localizedMessage}"))
        } catch (e: Exception) {
            emit(ApiResponse.Error("other : ${e.localizedMessage}"))
        }
    }
}