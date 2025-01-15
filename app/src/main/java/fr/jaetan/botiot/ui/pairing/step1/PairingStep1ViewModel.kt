package fr.jaetan.botiot.ui.pairing.step1

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiManager
import android.net.wifi.WifiNetworkSpecifier
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import fr.jaetan.botiot.helper.NavigationRoutes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

data class WifiNetwork(val ssid: String, val signalStrength: Int, val data: ScanResult)

class PairingStep1ViewModel(application: Application): AndroidViewModel(application) {
    private val wifiManager = application.getSystemService(Context.WIFI_SERVICE) as WifiManager
    var isScanning by mutableStateOf(false)
    var wifiNetworks = mutableStateListOf<WifiNetwork>()
        private set

    private val wifiScanReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == WifiManager.SCAN_RESULTS_AVAILABLE_ACTION) {
                val results = wifiManager.scanResults
                updateWifiList(results)
                isScanning = false
            }
        }
    }

    init {
        val intentFilter = IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
        application.registerReceiver(wifiScanReceiver, intentFilter)
        scanWifiNetworks()
    }

    override fun onCleared() {
        super.onCleared()
        getApplication<Application>().unregisterReceiver(wifiScanReceiver)
    }

    fun scanWifiNetworks() {
        viewModelScope.launch {
            isScanning = true
            wifiManager.startScan()
        }
    }

    fun connectToWifi(context: Context, wifi: WifiNetwork, navController: NavHostController) {
        val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val wifiConfig = WifiConfiguration().apply {
            SSID = wifi.ssid
        }
        val netId = wifiManager.addNetwork(wifiConfig)

        wifiManager.disconnect()
        wifiManager.enableNetwork(netId, true)
        wifiManager.reconnect()

        val currentSsid = wifiManager.connectionInfo.ssid
        if (currentSsid.contains("BopIoT", ignoreCase = true)) {
            navController.navigate(NavigationRoutes.pairingStep2)
        } else {
            Log.e("testt", "Impossible de se connecter à ESP32_AP")
        }
    }

    private fun updateWifiList(results: List<ScanResult>) {
        val networks = results.map { result ->
            WifiNetwork(result.SSID, result.level, result)
        }.filter { it.ssid.startsWith("BopIoT") }

        wifiNetworks.clear()
        wifiNetworks.addAll(networks)
    }
}