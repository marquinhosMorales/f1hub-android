package com.marquinhosmorales.f1hub.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.marquinhosmorales.f1hub.data.AppContainer
import com.marquinhosmorales.f1hub.data.FakeAppContainer
import com.marquinhosmorales.f1hub.navigation.Screen
import com.marquinhosmorales.f1hub.ui.components.F1HubBottomNavigation
import com.marquinhosmorales.f1hub.ui.screens.drivers.DriversScreen
import com.marquinhosmorales.f1hub.ui.screens.drivers.DriversViewModel
import com.marquinhosmorales.f1hub.ui.screens.races.RacesScreen
import com.marquinhosmorales.f1hub.ui.screens.standings.StandingsScreen
import com.marquinhosmorales.f1hub.ui.theme.F1HubTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun F1HubApp(
    appContainer: AppContainer,
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = {
            F1HubBottomNavigation(
                navController = navController
            )
        },
        modifier = Modifier.fillMaxSize()
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.Drivers.route
        ) {
            composable(Screen.Drivers.route) {
                val driversViewModel: DriversViewModel = viewModel(
                    factory = DriversViewModel.provideFactory(appContainer.driverRepository),
                )
                DriversScreen(driversViewModel)
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
        F1HubApp(FakeAppContainer())
    }
}

@Preview
@Composable
fun F1HubDarkThemePreview() {
    F1HubTheme(darkTheme = true) {
        F1HubApp(FakeAppContainer())
    }
}