package fr.jaetan.botiot.ui.pairing.step2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fr.jaetan.botiot.R
import fr.jaetan.botiot.helper.NavigationRoutes
import fr.jaetan.botiot.ui._shared.components.AppScaffold
import fr.jaetan.botiot.ui._shared.components.FilledButton

@Composable
fun PairingStep2View(navController: NavHostController) {
    AppScaffold {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 30.dp)
        ) {
            Box {}
            Description(Modifier)
            ContinueButton(Modifier.padding(bottom = 30.dp), navController)
        }
    }
}

@Composable
private fun Description(modifier: Modifier) {
    Text(
        text = "Click sur le liens, et connecte le module de jeux à un wifi.",
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleLarge,
        modifier = modifier
    )
}

@Composable
private fun ContinueButton(modifier: Modifier, navController: NavHostController) {
    val localUriHandler = LocalUriHandler.current
    FilledButton(
        title = R.string.next,
        modifier = modifier
    ) {
        // navController.navigate(NavigationRoutes.pairingStep3)
        localUriHandler.openUri("http://192.168.4.1")
    }
}