package com.marquinhosmorales.f1hub.network

import com.marquinhosmorales.f1hub.model.teams.TeamDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface TeamsApiService {
    @GET("api/teams/{teamId}")
    suspend fun getTeamDetail(@Path("teamId") teamId: String): TeamDetailResponse
}
