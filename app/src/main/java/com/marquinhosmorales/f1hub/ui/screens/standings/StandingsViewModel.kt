package com.marquinhosmorales.f1hub.ui.screens.standings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.marquinhosmorales.f1hub.data.standings.StandingsRepository
import com.marquinhosmorales.f1hub.model.standings.StandingsEntry
import com.marquinhosmorales.f1hub.ui.screens.BaseUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class StandingsUiState(
    override val isLoading: Boolean = false,
    override val isRefreshing: Boolean = false,
    override val error: String? = null,
    val driversStandings: List<StandingsEntry> = emptyList(),
) : BaseUiState(isLoading, isRefreshing, error)

class StandingsViewModel(
    private val standingsRepository: StandingsRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(StandingsUiState())
    val uiState: StateFlow<StandingsUiState> = _uiState.asStateFlow()

    init {
        fetchCurrentDriversStandings()
    }

    fun refresh() {
        fetchCurrentDriversStandings(isRefreshing = true)
    }

    private fun fetchCurrentDriversStandings(isRefreshing: Boolean = false) {
        viewModelScope.launch {
            _uiState.value = StandingsUiState(isLoading = true, isRefreshing = isRefreshing)
            try {
                val driversStandings = standingsRepository.getCurrentDriversStandings()
                _uiState.value = StandingsUiState(driversStandings = driversStandings)
            } catch (e: Exception) {
                _uiState.value = StandingsUiState(error = e.message ?: "Failed to load drivers")
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