package com.marquinhosmorales.f1hub.data.races

import android.util.Log
import com.marquinhosmorales.f1hub.model.races.Race
import com.marquinhosmorales.f1hub.network.RacesApiService
import kotlinx.serialization.SerializationException
import retrofit2.HttpException

class RacesRepositoryImpl(
    private val apiService: RacesApiService
) : RacesRepository {
    override suspend fun getCurrentRaces(): List<Race> {
        return try {
            val response = apiService.getCurrentRaces()
            Log.d("RacesRepository", "API response: $response")
            response.races
        } catch (e: SerializationException) {
            Log.e("RacesRepository", "Serialization error: ${e.message}", e)
            throw Exception("Serialization error: ${e.message}", e)
        } catch (e: HttpException) {
            Log.e("RacesRepository", "HTTP error: ${e.code()} ${e.message()}", e)
            throw Exception("HTTP error: ${e.code()} ${e.message()}", e)
        } catch (e: Exception) {
            Log.e("RacesRepository", "Unexpected error: ${e.message}", e)
            throw Exception("Unexpected error: ${e.message}", e)
        }
    }
}