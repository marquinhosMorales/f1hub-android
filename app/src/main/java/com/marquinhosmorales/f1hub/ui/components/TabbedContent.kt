package com.marquinhosmorales.f1hub.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marquinhosmorales.f1hub.ui.screens.BaseUiState
import com.marquinhosmorales.f1hub.ui.screens.ErrorScreen
import com.marquinhosmorales.f1hub.ui.screens.LoadingScreen
import com.marquinhosmorales.f1hub.ui.theme.F1HubTheme
import com.marquinhosmorales.f1hub.ui.theme.accentColor

@Composable
fun <T, S : BaseUiState> TabbedContent(
    tabs: List<String>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    uiState: S,
    accentColor: Color,
    getItems: (S, Int) -> List<T>,
    itemContent: @Composable (T) -> Unit,
    itemKey: (T) -> Any
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier.fillMaxWidth(),
            containerColor = accentColor,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    color = Color.White
                )
            }
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { onTabSelected(index) },
                    text = {
                        Text(
                            text = title,
                            style = if (selectedTabIndex == index) {
                                MaterialTheme.typography.bodyMedium
                            } else {
                                MaterialTheme.typography.labelLarge
                            },
                            color = if (selectedTabIndex == index) {
                                Color.White
                            } else {
                                Color.White.copy(alpha = 0.6f)
                            }
                        )
                    }
                )
            }
        }

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
                    items(getItems(uiState, selectedTabIndex), key = itemKey) { item ->
                        itemContent(item)
                    }
                }
            }
        }
    }
}

@Preview("TabbedContent", showBackground = true)
@Composable
fun StandingsScreenPreview() {
    data class PreviewUiState(
        override val isLoading: Boolean = false,
        override val isRefreshing: Boolean = false,
        override val error: String? = null,
        val tab1Items: List<String> = emptyList(),
        val tab2Items: List<String> = emptyList()
    ) : BaseUiState(isLoading, isRefreshing, error)

    F1HubTheme {
        var selectedTabIndex by remember { mutableIntStateOf(0) }

        val sampleItems = listOf("Item 1", "Item 2", "Item 3", "Item 4")
        val uiState = PreviewUiState(
            tab1Items = sampleItems.take(2),
            tab2Items = sampleItems.takeLast(2)
        )

        TabbedContent(
            tabs = listOf("Tab 1", "Tab 2"),
            selectedTabIndex = selectedTabIndex,
            onTabSelected = { selectedTabIndex = it },
            uiState = uiState,
            accentColor = accentColor,
            getItems = { state, tabIndex ->
                when (tabIndex) {
                    0 -> state.tab1Items
                    1 -> state.tab2Items
                    else -> emptyList()
                }
            },
            itemContent = { item ->
                Text(
                    text = item,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            itemKey = { item -> item }
        )
    }
}