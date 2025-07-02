package com.marquinhosmorales.f1hub.data

import com.marquinhosmorales.f1hub.data.drivers.DriverRepository
import com.marquinhosmorales.f1hub.data.drivers.DriverRepositoryImpl
import com.marquinhosmorales.f1hub.data.network.DriverApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create

interface AppContainer {
    val driverRepository: DriverRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://f1api.dev/"

    private val json = Json {
        ignoreUnknownKeys = true // Ignores unknown fields in JSON
        coerceInputValues = true // Converts nulls to default values
    }

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY // For debugging
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    private val driverApiService: DriverApiService by lazy {
        retrofit.create<DriverApiService>()
    }

    override val driverRepository: DriverRepository = DriverRepositoryImpl(driverApiService)
}