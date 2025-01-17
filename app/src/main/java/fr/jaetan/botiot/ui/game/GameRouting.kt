package fr.jaetan.botiot.ui.game

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import fr.jaetan.botiot.helper.NavigationRoutes
import fr.jaetan.botiot.ui.game.game.GameView
import fr.jaetan.botiot.ui.game.stats.StatsView

fun NavGraphBuilder.gameRouting(navController: NavHostController) {
    composable(NavigationRoutes.game) {
        GameView(navController)
    }
    composable(NavigationRoutes.stats) {
        StatsView(navController)
    }
}