package fr.jaetan.botiot.ui.pairing.step1

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import fr.jaetan.botiot.ui._shared.components.AppScaffold

@Composable
fun PairingStep1View(navController: NavHostController, viewModel: PairingStep1ViewModel = viewModel()) {
    AppScaffold {
        Content(viewModel, navController)
    }
}

@Composable
private fun Content(viewModel: PairingStep1ViewModel, navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Available Networks",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (viewModel.isScanning) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn {
                items(viewModel.wifiNetworks) { wifi ->
                    WifiListItem(wifi, viewModel, navController)
                }
            }
        }

        OutlinedButton(
            enabled = !viewModel.isScanning,
            onClick = { viewModel.scanWifiNetworks() },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
        ) {
            Text("Refresh")
        }
    }
}

@Composable
fun WifiListItem(wifi: WifiNetwork, viewModel: PairingStep1ViewModel, navController: NavHostController) {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                viewModel.connectToWifi(context, wifi, navController)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = wifi.ssid,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = "${wifi.signalStrength} dBm",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
