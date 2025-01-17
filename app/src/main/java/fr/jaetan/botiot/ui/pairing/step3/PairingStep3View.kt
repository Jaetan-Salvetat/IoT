package fr.jaetan.botiot.ui.pairing.step3

import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import fr.jaetan.botiot.ui._shared.components.AppScaffold

@Composable
fun PairingStep3View(navController: NavHostController) {
    AppScaffold {
        AndroidView(factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                loadUrl("http://192.168.4.1/wifi?")
            }
        })
    }
}