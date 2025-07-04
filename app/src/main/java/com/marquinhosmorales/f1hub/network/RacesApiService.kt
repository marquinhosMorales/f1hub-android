package com.marquinhosmorales.f1hub.network

import com.marquinhosmorales.f1hub.model.races.CurrentRacesResponse
import retrofit2.http.GET

interface RacesApiService {
    @GET("api/current")
    suspend fun getCurrentRaces(): CurrentRacesResponse
}