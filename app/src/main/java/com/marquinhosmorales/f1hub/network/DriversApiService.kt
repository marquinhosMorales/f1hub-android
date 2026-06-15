package com.marquinhosmorales.f1hub.network

import com.marquinhosmorales.f1hub.model.drivers.CurrentDriversResponse
import com.marquinhosmorales.f1hub.model.drivers.DriverDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DriversApiService {
    @GET("api/current/drivers")
    suspend fun getCurrentDrivers(): CurrentDriversResponse

    @GET("api/drivers/{driverId}")
    suspend fun getDriverDetail(@Path("driverId") driverId: String): DriverDetailResponse
}