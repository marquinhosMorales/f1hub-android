package com.marquinhosmorales.f1hub.ui.screens.drivers.driverDetail

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.marquinhosmorales.f1hub.data.drivers.DriversRepository
import com.marquinhosmorales.f1hub.data.wikipedia.WikipediaRepository
import com.marquinhosmorales.f1hub.model.drivers.Driver
import com.marquinhosmorales.f1hub.model.wikipedia.WikipediaSummary
import com.marquinhosmorales.f1hub.ui.screens.BaseUiState
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class DriverDetailUiState(
    override val isLoading: Boolean = false,
    override val isRefreshing: Boolean = false,
    override val error: String? = null,
    val driver: Driver? = null,
    val wikiSummary: WikipediaSummary? = null
) : BaseUiState(isLoading, isRefreshing, error)

class DriverDetailViewModel(
    private val driversRepository: DriversRepository,
    private val wikipediaRepository: WikipediaRepository,
    private val driverId: String,
    val wikiUrl: String
) : ViewModel() {
    private val _uiState = MutableStateFlow(DriverDetailUiState())
    val uiState: StateFlow<DriverDetailUiState> = _uiState.asStateFlow()

    init {
        fetchDriverDetail()
    }

    private fun fetchDriverDetail() {
        viewModelScope.launch {
            _uiState.value = DriverDetailUiState(isLoading = true)
            try {
                coroutineScope {
                    val wikiTitle = extractTitle(wikiUrl)

                    val driverDeferred = async { driversRepository.getDriverDetail(driverId) }
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

                    val driver = driverDeferred.await()
                    val wikiSummary = wikiDeferred.await()

                    if (driver != null) {
                        _uiState.value = DriverDetailUiState(
                            driver = driver,
                            wikiSummary = wikiSummary
                        )
                    } else {
                        _uiState.value = DriverDetailUiState(error = "Driver not found")
                    }
                }
            } catch (e: Exception) {
                _uiState.value =
                    DriverDetailUiState(error = e.message ?: "Failed to load driver details")
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
            driversRepository: DriversRepository,
            wikipediaRepository: WikipediaRepository,
            driverId: String,
            wikiUrl: String
        ): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return DriverDetailViewModel(
                        driversRepository,
                        wikipediaRepository,
                        driverId,
                        wikiUrl
                    ) as T
                }
            }
    }
}