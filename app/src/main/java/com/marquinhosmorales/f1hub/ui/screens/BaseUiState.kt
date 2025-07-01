package com.marquinhosmorales.f1hub.ui.screens

open class BaseUiState(
    open val isLoading: Boolean = false,
    open val isRefreshing: Boolean = false,
    open val error: String? = null
)