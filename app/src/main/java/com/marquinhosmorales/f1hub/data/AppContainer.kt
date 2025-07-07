package com.marquinhosmorales.f1hub.data

import com.marquinhosmorales.f1hub.data.drivers.DriversRepository
import com.marquinhosmorales.f1hub.data.drivers.DriversRepositoryImpl
import com.marquinhosmorales.f1hub.data.races.RacesRepository
import com.marquinhosmorales.f1hub.data.races.RacesRepositoryImpl
import com.marquinhosmorales.f1hub.data.standings.StandingsRepository
import com.marquinhosmorales.f1hub.data.standings.StandingsRepositoryImpl
import com.marquinhosmorales.f1hub.network.DriversApiService
import com.marquinhosmorales.f1hub.network.RacesApiService
import com.marquinhosmorales.f1hub.network.StandingsApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create

interface AppContainer {
    val driversRepository: DriversRepository
    val racesRepository: RacesRepository
    val standingsRepository: StandingsRepository
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

    private val driversApiService: DriversApiService by lazy {
        retrofit.create<DriversApiService>()
    }

    private val racesApiService: RacesApiService by lazy {
        retrofit.create<RacesApiService>()
    }

    private val standingsApiService: StandingsApiService by lazy {
        retrofit.create<StandingsApiService>()
    }

    override val driversRepository: DriversRepository = DriversRepositoryImpl(driversApiService)

    override val racesRepository: RacesRepository = RacesRepositoryImpl(racesApiService)

    override val standingsRepository: StandingsRepository =
        StandingsRepositoryImpl(standingsApiService)
}