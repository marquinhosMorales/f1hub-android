package com.marquinhosmorales.f1hub.ui.screens.standings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.marquinhosmorales.f1hub.data.standings.StandingsRepository
import com.marquinhosmorales.f1hub.model.standings.StandingsEntry
import com.marquinhosmorales.f1hub.ui.screens.BaseUiState
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class StandingsUiState(
    override val isLoading: Boolean = false,
    override val isRefreshing: Boolean = false,
    override val error: String? = null,
    val driversStandings: List<StandingsEntry> = emptyList(),
    val teamsStandings: List<StandingsEntry> = emptyList(),
) : BaseUiState(isLoading, isRefreshing, error)

class StandingsViewModel(
    private val standingsRepository: StandingsRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(StandingsUiState())
    val uiState: StateFlow<StandingsUiState> = _uiState.asStateFlow()

    init {
        fetchCurrentStandings()
    }

    fun refreshDriversStandings() {
        fetchCurrentDriversStandings(isRefreshing = true)
    }

    fun refreshTeamsStandings() {
        fetchCurrentTeamsStandings(isRefreshing = true)
    }

    private fun fetchCurrentStandings() {
        viewModelScope.launch {
            _uiState.value = StandingsUiState(
                isLoading = true
            )
            try {
                coroutineScope {
                    // Launch both network calls concurrently
                    val driversDeferred = async { standingsRepository.getCurrentDriversStandings() }
                    val teamsDeferred = async { standingsRepository.getCurrentTeamsStandings() }

                    // Await results from both calls
                    val driversStandings = driversDeferred.await()
                    val teamsStandings = teamsDeferred.await()

                    // Update UI state with both standings
                    _uiState.value = StandingsUiState(
                        driversStandings = driversStandings,
                        teamsStandings = teamsStandings
                    )
                }
            } catch (e: Exception) {
                _uiState.value = StandingsUiState(
                    error = e.message ?: "Failed to load standings"
                )
            }
        }
    }

    private fun fetchCurrentDriversStandings(isRefreshing: Boolean = false) {
        viewModelScope.launch {
            _uiState.value = StandingsUiState(
                isLoading = true,
                isRefreshing = isRefreshing,
                teamsStandings = _uiState.value.teamsStandings
            )
            try {
                val driversStandings = standingsRepository.getCurrentDriversStandings()
                _uiState.value = StandingsUiState(
                    driversStandings = driversStandings,
                    teamsStandings = _uiState.value.teamsStandings
                )
            } catch (e: Exception) {
                _uiState.value = StandingsUiState(
                    teamsStandings = _uiState.value.teamsStandings,
                    error = e.message ?: "Failed to load drivers standings"
                )
            }
        }
    }

    private fun fetchCurrentTeamsStandings(isRefreshing: Boolean = false) {
        viewModelScope.launch {
            _uiState.value = StandingsUiState(
                isLoading = true,
                isRefreshing = isRefreshing,
                driversStandings = _uiState.value.driversStandings
            )
            try {
                val teamsStandings = standingsRepository.getCurrentTeamsStandings()
                _uiState.value = StandingsUiState(
                    driversStandings = _uiState.value.driversStandings,
                    teamsStandings = teamsStandings
                )
            } catch (e: Exception) {
                _uiState.value = StandingsUiState(
                    driversStandings = _uiState.value.driversStandings,
                    error = e.message ?: "Failed to load teams standings"
                )
            }
        }
    }

    /**
     * Factory for StandingsViewModel that takes StandingsRepository as a dependency
     */
    companion object {
        fun provideFactory(standingsRepository: StandingsRepository): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return StandingsViewModel(standingsRepository) as T
                }
            }
    }
}