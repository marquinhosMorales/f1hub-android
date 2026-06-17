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

    object DriverDetail : Screen {
        override val route = "driver_detail/{driverId}/{wikiUrl}"
        override val title = "Driver Detail"
        override val icon: Int? = null

        fun createRoute(driverId: String, wikiUrl: String, prefix: String = "") =
            "${if (prefix.isNotEmpty()) "$prefix/" else ""}driver_detail/$driverId/${
                android.net.Uri.encode(
                    wikiUrl
                )
            }"
    }

    object TeamDetail : Screen {
        override val route = "team_detail/{teamId}/{wikiUrl}"
        override val title = "Team Detail"
        override val icon: Int? = null

        fun createRoute(teamId: String, wikiUrl: String, prefix: String = "") =
            "${if (prefix.isNotEmpty()) "$prefix/" else ""}team_detail/$teamId/${
                android.net.Uri.encode(
                    wikiUrl
                )
            }"
    }

    companion object {
        // These are the entry points for the bottom nav
        val bottomNavScreens = listOf(Drivers, Races, Standings)

        // Define Graph routes
        const val DRIVERS_GRAPH = "drivers_graph"
        const val RACES_GRAPH = "races_graph"
        const val STANDINGS_GRAPH = "standings_graph"
    }
}