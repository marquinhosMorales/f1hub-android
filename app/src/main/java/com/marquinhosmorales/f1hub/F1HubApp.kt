package com.marquinhosmorales.f1hub

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.marquinhosmorales.f1hub.navigation.Screen
import com.marquinhosmorales.f1hub.ui.components.F1HubBottomNavigation
import com.marquinhosmorales.f1hub.ui.screens.DriversScreen
import com.marquinhosmorales.f1hub.ui.screens.RacesScreen
import com.marquinhosmorales.f1hub.ui.screens.StandingsScreen
import com.marquinhosmorales.f1hub.ui.theme.F1HubTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun F1HubApp(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = {
            F1HubBottomNavigation(
                navController = navController
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Drivers.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Drivers.route) {
                DriversScreen()
            }
            composable(Screen.Races.route) {
                RacesScreen()
            }
            composable(Screen.Standings.route) {
                StandingsScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun F1HubPreview() {
    F1HubTheme {
        F1HubApp()
    }
}

@Preview
@Composable
fun F1HubDarkThemePreview() {
    F1HubTheme(darkTheme = true) {
        F1HubApp()
    }
}