package fr.jaetan.botiot.ui.onboarding

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import fr.jaetan.botiot.helper.NavigationRoutes
import fr.jaetan.botiot.ui.onboarding.welcome.WelcomeView

fun NavGraphBuilder.onboardingRoutes() {
    navigation(route = NavigationRoutes.Onboarding.name, startDestination = NavigationRoutes.Onboarding.welcome) {
        composable(NavigationRoutes.Onboarding.welcome) {
            WelcomeView()
        }
    }
}