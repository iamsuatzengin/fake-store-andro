package com.example.fakestoreandro.di

import android.util.Log
import com.example.fakestoreandro.BuildConfig
import com.example.fakestoreandro.util.Constants.CONNECT_TIMEOUT
import com.example.fakestoreandro.util.Constants.KTOR_LOGGER_TAG
import com.example.fakestoreandro.util.Constants.SOCKET_TIMEOUT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {


    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient = HttpClient(Android) {
        defaultRequest {
            url(BuildConfig.BASE_URL)
        }

        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.d(KTOR_LOGGER_TAG, "message : $message")
                }
            }
            level = LogLevel.BODY
        }

        engine {
            connectTimeout = CONNECT_TIMEOUT
            socketTimeout = SOCKET_TIMEOUT
        }
    }
}