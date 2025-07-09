package com.marquinhosmorales.f1hub.ui.screens.standings

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.marquinhosmorales.f1hub.data.standings.FakeStandingsRepository
import com.marquinhosmorales.f1hub.navigation.Screen
import com.marquinhosmorales.f1hub.ui.components.F1HubTopBar
import com.marquinhosmorales.f1hub.ui.components.TabbedContent
import com.marquinhosmorales.f1hub.ui.theme.F1HubTheme
import com.marquinhosmorales.f1hub.ui.theme.accentColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandingsScreen(
    viewModel: StandingsViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val standings by remember(selectedTabIndex, uiState) {
        derivedStateOf {
            if (selectedTabIndex == 0) uiState.driversStandings else uiState.teamsStandings
        }
    }

    Scaffold(
        topBar = {
            F1HubTopBar(title = Screen.Standings.title)
        },
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.systemBars
    ) { innerPadding ->
        PullToRefreshBox(
            isRefreshing = uiState.isRefreshing,
            onRefresh = { if (selectedTabIndex == 0) viewModel.refreshDriversStandings() else viewModel.refreshTeamsStandings() },
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            TabbedContent(
                tabs = listOf("Drivers", "Teams"),
                selectedTabIndex = selectedTabIndex,
                onTabSelected = { selectedTabIndex = it },
                uiState = uiState,
                accentColor = accentColor,
                getItems = { state, tabIndex ->
                    standings
                },
                itemContent = { standingsEntry ->
                    StandingsItem(standingsEntry)
                },
                itemKey = { standingsEntry ->
                    standingsEntry.id
                }
            )
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