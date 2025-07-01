package com.marquinhosmorales.f1hub.ui.screens.drivers

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marquinhosmorales.f1hub.data.drivers.mockDrivers
import com.marquinhosmorales.f1hub.navigation.Screen
import com.marquinhosmorales.f1hub.ui.components.F1HubTopBar
import com.marquinhosmorales.f1hub.ui.screens.ErrorScreen
import com.marquinhosmorales.f1hub.ui.screens.LoadingScreen
import com.marquinhosmorales.f1hub.ui.theme.F1HubTheme

@Composable
fun DriversScreen(
    viewModel: DriversViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    DriversScreenContent(
        uiState = uiState,
        onRefresh = { viewModel.refresh() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DriversScreenContent(
    uiState: DriversUiState,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            F1HubTopBar(title = Screen.Drivers.title)
        },
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.systemBars
    ) { innerPadding ->
        PullToRefreshBox(
            isRefreshing = uiState.isRefreshing,
            onRefresh = onRefresh,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when {
                    uiState.isLoading && !uiState.isRefreshing -> {
                        LoadingScreen()
                    }

                    uiState.error != null -> {
                        ErrorScreen(uiState.error)
                    }

                    else -> {
                        LazyColumn(
                            modifier = modifier
                                .fillMaxSize()
                                .padding(vertical = 8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            items(uiState.drivers, key = { it.id }) { driver ->
                                DriverItem(driver)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview("Drivers screen")
@Preview("Drivers screen (dark)", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DriversScreenPreview() {
    F1HubTheme {
        DriversScreenContent(
            uiState = DriversUiState(
                isLoading = false,
                error = null,
                drivers = mockDrivers
            ),
            onRefresh = {}
        )
    }
}