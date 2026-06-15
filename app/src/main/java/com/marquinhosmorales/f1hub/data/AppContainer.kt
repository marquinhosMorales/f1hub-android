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
import com.marquinhosmorales.f1hub.network.WikipediaApiService
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
    private val f1BaseUrl = "https://f1api.dev/"
    private val wikiBaseUrl = "https://en.wikipedia.org/"

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

    private val wikiClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .header("User-Agent", "F1Hub/1.0")
                .build()
            chain.proceed(request)
        }
        .build()

    private val f1Retrofit = Retrofit.Builder()
        .baseUrl(f1BaseUrl)
        .client(client)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    private val wikiRetrofit = Retrofit.Builder()
        .baseUrl(wikiBaseUrl)
        .client(wikiClient)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    private val driversApiService: DriversApiService by lazy {
        f1Retrofit.create<DriversApiService>()
    }

    private val wikiApiService: WikipediaApiService by lazy {
        wikiRetrofit.create<WikipediaApiService>()
    }

    private val racesApiService: RacesApiService by lazy {
        f1Retrofit.create<RacesApiService>()
    }

    private val standingsApiService: StandingsApiService by lazy {
        f1Retrofit.create<StandingsApiService>()
    }

    override val driversRepository: DriversRepository =
        DriversRepositoryImpl(driversApiService, wikiApiService)

    override val racesRepository: RacesRepository = RacesRepositoryImpl(racesApiService)

    override val standingsRepository: StandingsRepository =
        StandingsRepositoryImpl(standingsApiService)
}