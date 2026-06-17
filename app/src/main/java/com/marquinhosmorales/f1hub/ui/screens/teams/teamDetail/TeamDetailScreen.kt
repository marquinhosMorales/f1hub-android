package com.marquinhosmorales.f1hub.ui.screens.teams.teamDetail

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.marquinhosmorales.f1hub.data.teams.FakeTeamsRepository
import com.marquinhosmorales.f1hub.data.teams.mockRedBull
import com.marquinhosmorales.f1hub.data.wikipedia.FakeWikipediaRepository
import com.marquinhosmorales.f1hub.model.teams.TeamID
import com.marquinhosmorales.f1hub.ui.components.F1HubTopBar
import com.marquinhosmorales.f1hub.ui.screens.ErrorScreen
import com.marquinhosmorales.f1hub.ui.screens.LoadingScreen
import com.marquinhosmorales.f1hub.ui.theme.F1HubTheme

@Composable
fun TeamDetailScreen(
    viewModel: TeamDetailViewModel,
    navigateUp: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val uriHandler = LocalUriHandler.current

    Scaffold(
        topBar = {
            F1HubTopBar(
                title = "",
                canNavigateBack = true,
                navigateUp = navigateUp,
                actions = {
                    if (viewModel.wikiUrl.isNotEmpty()) {
                        IconButton(onClick = { uriHandler.openUri(viewModel.wikiUrl) }) {
                            Icon(
                                imageVector = Icons.Outlined.Info,
                                contentDescription = "Information",
                                tint = Color.White
                            )
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when {
                uiState.isLoading -> LoadingScreen()
                uiState.error != null -> ErrorScreen(uiState.error)
                uiState.team != null -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        val team = uiState.team!!
                        TeamDetailHeader(
                            team = team,
                            imageUrl = uiState.wikiSummary?.originalimage?.source
                                ?: uiState.wikiSummary?.thumbnail?.source
                        )
                        TeamDetailBody(
                            team = team,
                            biography = uiState.wikiSummary?.extract
                        )
                    }
                }
            }
        }
    }
}

@Preview("Team Detail Screen")
@Preview("Team Detail Screen (dark)", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun TeamDetailScreenPreview() {
    F1HubTheme {
        TeamDetailScreen(
            viewModel = viewModel(
                factory = TeamDetailViewModel.provideFactory(
                    FakeTeamsRepository(),
                    FakeWikipediaRepository(),
                    TeamID.RedBull.id,
                    mockRedBull.url
                )
            ),
            navigateUp = {}
        )
    }
}