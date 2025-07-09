package com.marquinhosmorales.f1hub.ui

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.marquinhosmorales.f1hub.ui.screens.races.RacesViewModel
import com.marquinhosmorales.f1hub.ui.screens.standings.StandingsScreen
import com.marquinhosmorales.f1hub.ui.screens.standings.StandingsViewModel
import com.marquinhosmorales.f1hub.ui.theme.F1HubTheme

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
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Drivers.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding)
        ) {
            composable(Screen.Drivers.route) {
                val driversViewModel: DriversViewModel = viewModel(
                    factory = DriversViewModel.provideFactory(appContainer.driversRepository),
                )
                DriversScreen(driversViewModel)
            }
            composable(Screen.Races.route) {
                val racesViewModel: RacesViewModel = viewModel(
                    factory = RacesViewModel.provideFactory(appContainer.racesRepository),
                )
                RacesScreen(racesViewModel)
            }
            composable(Screen.Standings.route) {
                val standingsViewModel: StandingsViewModel = viewModel(
                    factory = StandingsViewModel.provideFactory(appContainer.standingsRepository),
                )
                StandingsScreen(standingsViewModel)
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