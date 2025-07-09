package com.marquinhosmorales.f1hub.ui.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.marquinhosmorales.f1hub.ui.theme.F1HubTheme
import com.marquinhosmorales.f1hub.ui.theme.accentColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun F1HubTopBar(
    title: String
) {
    CenterAlignedTopAppBar(
        windowInsets = WindowInsets(0, 0, 0, 0),
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = accentColor,
            titleContentColor = Color.White
        )
    )
}

@Preview(showBackground = true)
@Composable
fun F1HubTopBarPreview() {
    F1HubTheme {
        F1HubTopBar(
            title = "Title"
        )
    }
}