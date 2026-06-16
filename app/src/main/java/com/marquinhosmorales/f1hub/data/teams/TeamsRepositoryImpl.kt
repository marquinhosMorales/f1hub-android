package com.marquinhosmorales.f1hub.data.teams

import android.util.Log
import com.marquinhosmorales.f1hub.model.teams.Team
import com.marquinhosmorales.f1hub.network.TeamsApiService

class TeamsRepositoryImpl(
    private val teamsApiService: TeamsApiService
) : TeamsRepository {
    override suspend fun getTeamDetail(teamId: String): Team? {
        return try {
            val response = teamsApiService.getTeamDetail(teamId)
            response.teams.firstOrNull()
        } catch (e: Exception) {
            Log.e("TeamsRepository", "Error fetching team $teamId: ${e.message}", e)
            null
        }
    }
}