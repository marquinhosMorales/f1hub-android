package com.marquinhosmorales.f1hub.ui.screens.standings

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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.marquinhosmorales.f1hub.data.standings.FakeStandingsRepository
import com.marquinhosmorales.f1hub.navigation.Screen
import com.marquinhosmorales.f1hub.ui.components.F1HubTopBar
import com.marquinhosmorales.f1hub.ui.screens.ErrorScreen
import com.marquinhosmorales.f1hub.ui.screens.LoadingScreen
import com.marquinhosmorales.f1hub.ui.theme.F1HubTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandingsScreen(
    viewModel: StandingsViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    Scaffold(
        topBar = {
            F1HubTopBar(title = Screen.Standings.title)
        },
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.systemBars
    ) { innerPadding ->
        PullToRefreshBox(
            isRefreshing = uiState.isRefreshing,
            onRefresh = { viewModel.refresh() },
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
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(vertical = 8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            items(uiState.driversStandings, key = { it.id }) { standingsEntry ->
                                StandingsItem(standingsEntry)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview("Standings screen")
@Preview("Standings screen (dark)", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun StandingsScreenPreview() {
    F1HubTheme {
        StandingsScreen(
            viewModel = viewModel(
                factory = StandingsViewModel.provideFactory(FakeStandingsRepository()),
            )
        )
    }
}