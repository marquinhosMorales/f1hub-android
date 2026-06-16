package com.marquinhosmorales.f1hub.data.drivers

import android.util.Log
import com.marquinhosmorales.f1hub.model.drivers.Driver
import com.marquinhosmorales.f1hub.network.DriversApiService
import kotlinx.serialization.SerializationException
import retrofit2.HttpException

class DriversRepositoryImpl(
    private val f1ApiService: DriversApiService
) : DriversRepository {
    override suspend fun getCurrentDrivers(): List<Driver> {
        return try {
            val response = f1ApiService.getCurrentDrivers()
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

    override suspend fun getDriverDetail(driverId: String): Driver? {
        return try {
            val response = f1ApiService.getDriverDetail(driverId)
            response.drivers.firstOrNull()
        } catch (e: Exception) {
            Log.e("DriverRepository", "Error fetching driver $driverId: ${e.message}", e)
            null
        }
    }
}