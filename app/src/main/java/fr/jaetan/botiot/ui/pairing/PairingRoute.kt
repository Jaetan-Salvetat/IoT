package fr.jaetan.botiot.ui.pairing

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import fr.jaetan.botiot.helper.NavigationRoutes
import fr.jaetan.botiot.ui.pairing.step1.PairingStep1View
import fr.jaetan.botiot.ui.pairing.step2.PairingStep2View
import fr.jaetan.botiot.ui.pairing.step3.PairingStep3View

fun NavGraphBuilder.pairingRoutes(navController: NavHostController) {
    composable(NavigationRoutes.pairingStep1) {
        PairingStep1View(navController)
    }
    composable(NavigationRoutes.pairingStep2) {
        PairingStep2View(navController)
    }
    composable(NavigationRoutes.pairingStep3) {
        PairingStep3View(navController)
    }
}

