package fr.jaetan.botiot.ui.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import fr.jaetan.botiot.helper.NavigationRoutes

fun NavGraphBuilder.homeRoutes(navController: NavHostController) {
    composable(NavigationRoutes.home) {
        HomeView(navController)
    }
}