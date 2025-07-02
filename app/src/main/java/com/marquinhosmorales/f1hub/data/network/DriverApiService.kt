package com.marquinhosmorales.f1hub.data.network

import com.marquinhosmorales.f1hub.model.drivers.CurrentDriversResponse
import retrofit2.http.GET

interface DriverApiService {
    @GET("api/current/drivers")
    suspend fun getCurrentDrivers(): CurrentDriversResponse
}