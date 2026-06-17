package com.marquinhosmorales.f1hub.data.teams

import com.marquinhosmorales.f1hub.model.teams.Team

interface TeamsRepository {
    suspend fun getTeamDetail(teamId: String): Team?
}