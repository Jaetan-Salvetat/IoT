package fr.jaetan.botiot.ui.onboarding.welcome

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import   androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import fr.jaetan.botiot.ui._shared.components.AppScaffold
import fr.jaetan.botiot.R
import fr.jaetan.botiot.ui._shared.components.FilledButton

@Composable
fun WelcomeView() {
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
            ContinueButton(Modifier.padding(bottom = 30.dp))
        }
    }
}

@Composable
private fun Description(modifier: Modifier) {
    Text(
        text = stringResource(R.string.welcome_description),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleLarge,
        modifier = modifier
    )
}

@Composable
private fun ContinueButton(modifier: Modifier) {
    FilledButton(
        title = R.string.scan_qrcode,
        modifier = modifier
    ) {
        Log.d("ContinueButton", "Continue button pressed")
        // TODO: NAVIGATE TO SCAN QR CODE
    }
}