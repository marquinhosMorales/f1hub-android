package com.marquinhosmorales.f1hub.ui.screens.drivers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.marquinhosmorales.f1hub.data.drivers.DriversRepository
import com.marquinhosmorales.f1hub.model.drivers.Driver
import com.marquinhosmorales.f1hub.ui.screens.BaseUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class DriversUiState(
    override val isLoading: Boolean = false,
    override val isRefreshing: Boolean = false,
    override val error: String? = null,
    val drivers: List<Driver> = emptyList()
) : BaseUiState(isLoading, isRefreshing, error)

class DriversViewModel(
    private val driversRepository: DriversRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(DriversUiState())
    val uiState: StateFlow<DriversUiState> = _uiState.asStateFlow()

    init {
        fetchDrivers()
    }

    fun refresh() {
        fetchDrivers(isRefreshing = true)
    }

    private fun fetchDrivers(isRefreshing: Boolean = false) {
        viewModelScope.launch {
            _uiState.value = DriversUiState(isLoading = true, isRefreshing = isRefreshing)
            try {
                val drivers = driversRepository.getCurrentDrivers()
                _uiState.value = DriversUiState(drivers = drivers)
            } catch (e: Exception) {
                _uiState.value = DriversUiState(error = e.message ?: "Failed to load drivers")
            }
        }
    }

    /**
     * Factory for DriversViewModel that takes DriverRepository as a dependency
     */
    companion object {
        fun provideFactory(driversRepository: DriversRepository): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return DriversViewModel(driversRepository) as T
            }
        }
    }
}