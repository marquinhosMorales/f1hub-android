package com.marquinhosmorales.f1hub.data.teams

import com.marquinhosmorales.f1hub.model.teams.Team

class FakeTeamsRepository : TeamsRepository {
    override suspend fun getTeamDetail(teamId: String): Team? {
        return mockTeams.find { it.id == teamId }
    }
}