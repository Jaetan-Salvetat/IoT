package fr.jaetan.botiot.ui.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import fr.jaetan.botiot.helper.NavigationRoutes

fun NavGraphBuilder.homeRoutes() {
    navigation(route = NavigationRoutes.Home.name, startDestination = NavigationRoutes.Home.name) {
        composable(NavigationRoutes.Home.name) {

        }
    }
}