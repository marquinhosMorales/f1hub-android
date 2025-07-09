package com.marquinhosmorales.f1hub.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.marquinhosmorales.f1hub.F1HubApplication
import com.marquinhosmorales.f1hub.ui.theme.F1HubTheme
import com.marquinhosmorales.f1hub.ui.theme.accentColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val appContainer = (application as F1HubApplication).container
        setContent {
            F1HubTheme(statusBarColor = accentColor) {
                F1HubApp(appContainer)
            }
        }
    }
}