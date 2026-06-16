package com.marquinhosmorales.f1hub.ui.components

import android.util.Log
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.marquinhosmorales.f1hub.navigation.Screen
import com.marquinhosmorales.f1hub.ui.theme.F1HubTheme
import com.marquinhosmorales.f1hub.ui.theme.accentColor

@Composable
fun F1HubBottomNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestinationNode = navBackStackEntry?.destination

    NavigationBar(
        modifier = modifier
    ) {
        Screen.bottomNavScreens.forEach { screen ->
            val graphRoute = when (screen) {
                Screen.Drivers -> Screen.DRIVERS_GRAPH
                Screen.Races -> Screen.RACES_GRAPH
                Screen.Standings -> Screen.STANDINGS_GRAPH
                else -> screen.route
            }

            val isSelected =
                currentDestinationNode?.hierarchy?.any { it.route == graphRoute } == true

            NavigationBarItem(
                icon = {
                    screen.icon?.let { iconRes ->
                        Icon(
                            painter = painterResource(id = iconRes),
                            contentDescription = screen.title
                        )
                    }
                },
                label = { Text(screen.title) },
                selected = isSelected,
                onClick = {
                    if (!isSelected) {
                        Log.d("F1HubBottomNavigation", "Navigating to graph: $graphRoute")
                        navController.navigate(graphRoute) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = accentColor,
                    selectedTextColor = accentColor,
                    indicatorColor = accentColor.copy(alpha = 0.1f)
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun F1HubBottomNavigationPreview() {
    F1HubTheme {
        F1HubBottomNavigation(
            navController = rememberNavController(),
            modifier = Modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun F1HubBottomNavigationDarkPreview() {
    F1HubTheme(darkTheme = true) {
        F1HubBottomNavigation(
            navController = rememberNavController(),
            modifier = Modifier
        )
    }
}