package com.marquinhosmorales.f1hub.ui

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.marquinhosmorales.f1hub.data.AppContainer
import com.marquinhosmorales.f1hub.data.FakeAppContainer
import com.marquinhosmorales.f1hub.navigation.Screen
import com.marquinhosmorales.f1hub.ui.components.F1HubBottomNavigation
import com.marquinhosmorales.f1hub.ui.screens.drivers.DriversScreen
import com.marquinhosmorales.f1hub.ui.screens.drivers.DriversViewModel
import com.marquinhosmorales.f1hub.ui.screens.drivers.driverDetail.DriverDetailScreen
import com.marquinhosmorales.f1hub.ui.screens.drivers.driverDetail.DriverDetailViewModel
import com.marquinhosmorales.f1hub.ui.screens.races.RacesScreen
import com.marquinhosmorales.f1hub.ui.screens.races.RacesViewModel
import com.marquinhosmorales.f1hub.ui.screens.standings.StandingsScreen
import com.marquinhosmorales.f1hub.ui.screens.standings.StandingsViewModel
import com.marquinhosmorales.f1hub.ui.screens.teams.teamDetail.TeamDetailScreen
import com.marquinhosmorales.f1hub.ui.screens.teams.teamDetail.TeamDetailViewModel
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
            startDestination = Screen.DRIVERS_GRAPH,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding)
        ) {
            driversGraph(navController, appContainer)
            racesGraph(navController, appContainer)
            standingsGraph(navController, appContainer)
        }
    }
}

private fun NavGraphBuilder.driversGraph(
    navController: NavHostController,
    appContainer: AppContainer
) {
    navigation(
        startDestination = Screen.Drivers.route,
        route = Screen.DRIVERS_GRAPH
    ) {
        composable(Screen.Drivers.route) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(Screen.DRIVERS_GRAPH)
            }
            val driversViewModel: DriversViewModel = viewModel(
                viewModelStoreOwner = parentEntry,
                factory = DriversViewModel.provideFactory(appContainer.driversRepository),
            )
            DriversScreen(driversViewModel, onDriverClick = { driverId, wikiUrl ->
                navController.navigate(
                    Screen.DriverDetail.createRoute(
                        driverId,
                        wikiUrl,
                        "drivers"
                    )
                )
            })
        }
        composable(
            route = "drivers/" + Screen.DriverDetail.route,
            arguments = listOf(
                navArgument("driverId") { type = NavType.StringType },
                navArgument("wikiUrl") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val driverId = backStackEntry.arguments?.getString("driverId") ?: ""
            val wikiUrl = backStackEntry.arguments?.getString("wikiUrl") ?: ""
            val driverDetailViewModel: DriverDetailViewModel = viewModel(
                factory = DriverDetailViewModel.provideFactory(
                    appContainer.driversRepository,
                    appContainer.wikipediaRepository,
                    driverId,
                    wikiUrl
                ),
            )
            DriverDetailScreen(
                viewModel = driverDetailViewModel,
                navigateUp = { navController.navigateUp() }
            )
        }
    }
}

private fun NavGraphBuilder.racesGraph(
    navController: NavHostController,
    appContainer: AppContainer
) {
    navigation(
        startDestination = Screen.Races.route,
        route = Screen.RACES_GRAPH
    ) {
        composable(Screen.Races.route) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(Screen.RACES_GRAPH)
            }
            val racesViewModel: RacesViewModel = viewModel(
                viewModelStoreOwner = parentEntry,
                factory = RacesViewModel.provideFactory(appContainer.racesRepository),
            )
            RacesScreen(racesViewModel)
        }
    }
}

private fun NavGraphBuilder.standingsGraph(
    navController: NavHostController,
    appContainer: AppContainer
) {
    navigation(
        startDestination = Screen.Standings.route,
        route = Screen.STANDINGS_GRAPH
    ) {
        composable(Screen.Standings.route) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(Screen.STANDINGS_GRAPH)
            }
            val standingsViewModel: StandingsViewModel = viewModel(
                viewModelStoreOwner = parentEntry,
                factory = StandingsViewModel.provideFactory(appContainer.standingsRepository),
            )
            StandingsScreen(
                viewModel = standingsViewModel,
                onDriverClick = { driverId, wikiUrl ->
                    navController.navigate(
                        Screen.DriverDetail.createRoute(
                            driverId,
                            wikiUrl,
                            "standings"
                        )
                    )
                },
                onTeamClick = { teamId, wikiUrl ->
                    navController.navigate(
                        Screen.TeamDetail.createRoute(
                            teamId,
                            wikiUrl,
                            "standings"
                        )
                    )
                }
            )
        }
        composable(
            route = "standings/" + Screen.DriverDetail.route,
            arguments = listOf(
                navArgument("driverId") { type = NavType.StringType },
                navArgument("wikiUrl") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val driverId = backStackEntry.arguments?.getString("driverId") ?: ""
            val wikiUrl = backStackEntry.arguments?.getString("wikiUrl") ?: ""
            val driverDetailViewModel: DriverDetailViewModel = viewModel(
                factory = DriverDetailViewModel.provideFactory(
                    appContainer.driversRepository,
                    appContainer.wikipediaRepository,
                    driverId,
                    wikiUrl
                ),
            )
            DriverDetailScreen(
                viewModel = driverDetailViewModel,
                navigateUp = { navController.navigateUp() }
            )
        }
        composable(
            route = "standings/" + Screen.TeamDetail.route,
            arguments = listOf(
                navArgument("teamId") { type = NavType.StringType },
                navArgument("wikiUrl") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val teamId = backStackEntry.arguments?.getString("teamId") ?: ""
            val wikiUrl = backStackEntry.arguments?.getString("wikiUrl") ?: ""
            val teamDetailViewModel: TeamDetailViewModel = viewModel(
                factory = TeamDetailViewModel.provideFactory(
                    appContainer.teamsRepository,
                    appContainer.wikipediaRepository,
                    teamId,
                    wikiUrl
                ),
            )
            TeamDetailScreen(
                viewModel = teamDetailViewModel,
                navigateUp = { navController.navigateUp() }
            )
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