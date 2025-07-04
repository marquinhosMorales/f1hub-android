package com.marquinhosmorales.f1hub.ui.screens.races

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.marquinhosmorales.f1hub.data.races.RacesRepository
import com.marquinhosmorales.f1hub.model.races.Race
import com.marquinhosmorales.f1hub.ui.screens.BaseUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class RacesUiState(
    override val isLoading: Boolean = false,
    override val isRefreshing: Boolean = false,
    override val error: String? = null,
    val races: List<Race> = emptyList()
) : BaseUiState(isLoading, isRefreshing, error)

class RacesViewModel(
    private val racesRepository: RacesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(RacesUiState())
    val uiState: StateFlow<RacesUiState> = _uiState.asStateFlow()

    init {
        fetchRaces()
    }

    fun refresh() {
        fetchRaces(isRefreshing = true)
    }

    private fun fetchRaces(isRefreshing: Boolean = false) {
        viewModelScope.launch {
            _uiState.value = RacesUiState(isLoading = true, isRefreshing = isRefreshing)
            try {
                val races = racesRepository.getCurrentRaces()
                _uiState.value = RacesUiState(races = races)
            } catch (e: Exception) {
                _uiState.value = RacesUiState(error = e.message ?: "Failed to load races")
            }
        }
    }

    /**
     * Factory for RacesViewModel that takes RacesRepository as a dependency
     */
    companion object {
        fun provideFactory(racesRepository: RacesRepository): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return RacesViewModel(racesRepository) as T
            }
        }
    }
}