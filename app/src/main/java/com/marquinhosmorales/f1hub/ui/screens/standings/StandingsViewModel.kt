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
    val selectedTabIndex: Int = 0
) : BaseUiState(isLoading, isRefreshing, error)

class StandingsViewModel(
    private val standingsRepository: StandingsRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(StandingsUiState())
    val uiState: StateFlow<StandingsUiState> = _uiState.asStateFlow()

    init {
        fetchCurrentStandings()
    }

    fun updateSelectedTab(index: Int) {
        _uiState.value = _uiState.value.copy(selectedTabIndex = index)
    }

    fun refreshDriversStandings() {
        fetchCurrentDriversStandings(isRefreshing = true)
    }

    fun refreshTeamsStandings() {
        fetchCurrentTeamsStandings(isRefreshing = true)
    }

    private fun fetchCurrentStandings() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
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
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        driversStandings = driversStandings,
                        teamsStandings = teamsStandings
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Failed to load standings"
                )
            }
        }
    }

    private fun fetchCurrentDriversStandings(isRefreshing: Boolean = false) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                isRefreshing = isRefreshing
            )
            try {
                val driversStandings = standingsRepository.getCurrentDriversStandings()
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isRefreshing = false,
                    driversStandings = driversStandings
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isRefreshing = false,
                    error = e.message ?: "Failed to load drivers standings"
                )
            }
        }
    }

    private fun fetchCurrentTeamsStandings(isRefreshing: Boolean = false) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                isRefreshing = isRefreshing
            )
            try {
                val teamsStandings = standingsRepository.getCurrentTeamsStandings()
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isRefreshing = false,
                    teamsStandings = teamsStandings
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isRefreshing = false,
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