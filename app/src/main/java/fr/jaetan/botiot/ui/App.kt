package fr.jaetan.botiot.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import fr.jaetan.botiot.helper.NavigationRoutes
import fr.jaetan.botiot.ui.home.homeRoutes
import fr.jaetan.botiot.ui.onboarding.onboardingRoutes
import fr.jaetan.botiot.ui.qrcode_scan.qrcodeScanRoutes

@Composable
fun App() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = NavigationRoutes.Onboarding.name) {
        homeRoutes()
        onboardingRoutes()
        qrcodeScanRoutes()
    }
}