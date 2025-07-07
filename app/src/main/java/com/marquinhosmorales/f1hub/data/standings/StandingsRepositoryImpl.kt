package com.marquinhosmorales.f1hub.data.standings

import android.util.Log
import com.marquinhosmorales.f1hub.model.standings.StandingsEntry
import com.marquinhosmorales.f1hub.network.StandingsApiService
import kotlinx.serialization.SerializationException
import retrofit2.HttpException

class StandingsRepositoryImpl(
    private val apiService: StandingsApiService
) : StandingsRepository {
    override suspend fun getCurrentDriversStandings(): List<StandingsEntry> {
        return try {
            val response = apiService.getCurrentDriversStandings()
            Log.d("StandingsRepository", "API response: $response")
            response.driversStandings
        } catch (e: SerializationException) {
            Log.e("StandingsRepository", "Serialization error: ${e.message}", e)
            throw Exception("Serialization error: ${e.message}", e)
        } catch (e: HttpException) {
            Log.e("StandingsRepository", "HTTP error: ${e.code()} ${e.message()}", e)
            throw Exception("HTTP error: ${e.code()} ${e.message()}", e)
        } catch (e: Exception) {
            Log.e("StandingsRepository", "Unexpected error: ${e.message}", e)
            throw Exception("Unexpected error: ${e.message}", e)
        }
    }

    override suspend fun getCurrentTeamsStandings(): List<StandingsEntry> {
        return try {
            val response = apiService.getCurrentTeamsStandings()
            Log.d("StandingsRepository", "API response: $response")
            response.teamsStandings
        } catch (e: SerializationException) {
            Log.e("StandingsRepository", "Serialization error: ${e.message}", e)
            throw Exception("Serialization error: ${e.message}", e)
        } catch (e: HttpException) {
            Log.e("StandingsRepository", "HTTP error: ${e.code()} ${e.message()}", e)
            throw Exception("HTTP error: ${e.code()} ${e.message()}", e)
        } catch (e: Exception) {
            Log.e("StandingsRepository", "Unexpected error: ${e.message}", e)
            throw Exception("Unexpected error: ${e.message}", e)
        }
    }
}