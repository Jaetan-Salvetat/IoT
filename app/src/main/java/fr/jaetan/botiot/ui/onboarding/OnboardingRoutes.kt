package fr.jaetan.botiot.ui.onboarding

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import fr.jaetan.botiot.helper.NavigationRoutes

fun NavGraphBuilder.onboardingRoutes() {
    navigation(route = NavigationRoutes.Onboarding.name, startDestination = NavigationRoutes.Onboarding.name) {
        composable(NavigationRoutes.Onboarding.name) {
        }
    }
}