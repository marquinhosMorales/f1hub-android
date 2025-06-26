package com.marquinhosmorales.f1hub.navigation

import com.marquinhosmorales.f1hub.R

sealed interface Screen {
    val route: String
    val title: String
    val icon: Int?

    object Drivers : Screen {
        override val route = "drivers"
        override val title = "Drivers"
        override val icon = R.drawable.ic_steering_wheel
    }

    object Races : Screen {
        override val route = "races"
        override val title = "Races"
        override val icon = R.drawable.ic_race_flag
    }

    object Standings : Screen {
        override val route = "standings"
        override val title = "Standings"
        override val icon = R.drawable.ic_trophy
    }

    companion object {
        val bottomNavScreens = listOf(Drivers, Races, Standings)
    }
}