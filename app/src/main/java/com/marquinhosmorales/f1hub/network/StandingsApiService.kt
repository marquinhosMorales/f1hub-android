package com.marquinhosmorales.f1hub.network

import com.marquinhosmorales.f1hub.model.standings.CurrentDriversStandingsResponse
import com.marquinhosmorales.f1hub.model.standings.CurrentTeamsStandingsResponse
import retrofit2.http.GET

interface StandingsApiService {
    @GET("api/current/drivers-championship")
    suspend fun getCurrentDriversStandings(): CurrentDriversStandingsResponse

    @GET("api/current/constructors-championship")
    suspend fun getCurrentTeamsStandings(): CurrentTeamsStandingsResponse
}