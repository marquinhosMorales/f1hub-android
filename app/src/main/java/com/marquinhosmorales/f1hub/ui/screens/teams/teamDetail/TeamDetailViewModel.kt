package com.marquinhosmorales.f1hub.ui.screens.teams.teamDetail

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.marquinhosmorales.f1hub.data.teams.TeamsRepository
import com.marquinhosmorales.f1hub.data.wikipedia.WikipediaRepository
import com.marquinhosmorales.f1hub.model.teams.Team
import com.marquinhosmorales.f1hub.model.wikipedia.WikipediaSummary
import com.marquinhosmorales.f1hub.ui.screens.BaseUiState
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class TeamDetailUiState(
    override val isLoading: Boolean = false,
    override val isRefreshing: Boolean = false,
    override val error: String? = null,
    val team: Team? = null,
    val wikiSummary: WikipediaSummary? = null
) : BaseUiState(isLoading, isRefreshing, error)

class TeamDetailViewModel(
    private val teamsRepository: TeamsRepository,
    private val wikipediaRepository: WikipediaRepository,
    private val teamId: String,
    val wikiUrl: String
) : ViewModel() {
    private val _uiState = MutableStateFlow(TeamDetailUiState())
    val uiState: StateFlow<TeamDetailUiState> = _uiState.asStateFlow()

    init {
        fetchTeamDetail()
    }

    private fun fetchTeamDetail() {
        viewModelScope.launch {
            _uiState.value = TeamDetailUiState(isLoading = true)
            try {
                coroutineScope {
                    val wikiTitle = extractTitle(wikiUrl)

                    val teamDeferred = async { teamsRepository.getTeamDetail(teamId) }
                    val wikiDeferred = async {
                        try {
                            if (wikiTitle.isNotEmpty()) {
                                wikipediaRepository.getWikipediaSummary(wikiTitle)
                            } else {
                                null
                            }
                        } catch (e: Exception) {
                            null
                        }
                    }

                    val team = teamDeferred.await()
                    val wikiSummary = wikiDeferred.await()

                    if (team != null) {
                        _uiState.value = TeamDetailUiState(
                            team = team,
                            wikiSummary = wikiSummary
                        )
                    } else {
                        _uiState.value = TeamDetailUiState(error = "Team not found")
                    }
                }
            } catch (e: Exception) {
                _uiState.value =
                    TeamDetailUiState(error = e.message ?: "Failed to load team details")
            }
        }
    }

    private fun extractTitle(url: String): String {
        return try {
            val decodedUrl = Uri.decode(url)
            decodedUrl.substringAfterLast("/")
        } catch (e: Exception) {
            ""
        }
    }

    companion object {
        fun provideFactory(
            teamsRepository: TeamsRepository,
            wikipediaRepository: WikipediaRepository,
            teamId: String,
            wikiUrl: String
        ): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return TeamDetailViewModel(
                        teamsRepository,
                        wikipediaRepository,
                        teamId,
                        wikiUrl
                    ) as T
                }
            }
    }
}