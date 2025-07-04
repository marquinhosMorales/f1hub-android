package com.marquinhosmorales.f1hub.data.drivers

import android.util.Log
import com.marquinhosmorales.f1hub.model.drivers.Driver
import com.marquinhosmorales.f1hub.network.DriversApiService
import kotlinx.serialization.SerializationException
import retrofit2.HttpException

class DriversRepositoryImpl(
    private val apiService: DriversApiService
) : DriversRepository {
    override suspend fun getCurrentDrivers(): List<Driver> {
        return try {
            val response = apiService.getCurrentDrivers()
            Log.d("DriverRepository", "API response: $response")
            response.drivers
        } catch (e: SerializationException) {
            Log.e("DriverRepository", "Serialization error: ${e.message}", e)
            throw Exception("Serialization error: ${e.message}", e)
        } catch (e: HttpException) {
            Log.e("DriverRepository", "HTTP error: ${e.code()} ${e.message()}", e)
            throw Exception("HTTP error: ${e.code()} ${e.message()}", e)
        } catch (e: Exception) {
            Log.e("DriverRepository", "Unexpected error: ${e.message}", e)
            throw Exception("Unexpected error: ${e.message}", e)
        }
    }
}