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
    currentDestination: String? = null,
) {
    val currentRoute by navController.currentBackStackEntryAsState()
    val currentDestination = currentDestination ?: currentRoute?.destination?.route

    NavigationBar(
        modifier = modifier
    ) {
        Screen.bottomNavScreens.forEach { screen ->
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
                selected = currentDestination == screen.route,
                onClick = {
                    if (currentDestination != screen.route) {
                        Log.d("F1HubBottomNavigation", "Navigating to: ${screen.route}")
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
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
            modifier = Modifier,
            currentDestination = Screen.Drivers.route
        )
    }
}

@Preview(showBackground = true)
@Composable
fun F1HubBottomNavigationDarkPreview() {
    F1HubTheme(darkTheme = true) {
        F1HubBottomNavigation(
            navController = rememberNavController(),
            modifier = Modifier,
            currentDestination = Screen.Drivers.route
        )
    }
}