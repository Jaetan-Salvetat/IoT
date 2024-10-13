package fr.jaetan.botiot.ui.qrcode_scan

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import fr.jaetan.botiot.helper.NavigationRoutes

fun NavGraphBuilder.qrcodeScanRoutes() {
    navigation(route = NavigationRoutes.QRCodeScan.name, startDestination = NavigationRoutes.QRCodeScan.name) {
        composable(NavigationRoutes.QRCodeScan.name) {
        }
    }
}