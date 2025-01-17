package fr.jaetan.botiot.networking

import android.util.Log
import fr.jaetan.botiot.networking.models.MqttResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttException

class MqttService {
    private val brokerUrl = "tcp://broker.emqx.io:1883"
    private val clientId = "mqttx_" + (System.currentTimeMillis() / 1000).toString()
    private lateinit var client: MqttClient

    fun connect(topic: String, callback: (MqttResponse?, isError: Boolean) -> Unit) {
        try {
            // Créez un client
            client = MqttClient(brokerUrl, clientId, null)

            // Options de connexion
            val options = MqttConnectOptions().apply {
                isCleanSession = true
                connectionTimeout = 10
                keepAliveInterval = 20
            }

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    client.connect(options)
                    Log.d("MqttService", "Connected to $brokerUrl")

                    client.subscribe(topic) { topic, message ->
                        try {
                            val json = Json { ignoreUnknownKeys = true }
                            Log.d("MqttService", "Received message: ${message.payload.toString(Charsets.UTF_8)}")
                            val response = json.decodeFromString<MqttResponse>(message.payload.toString(Charsets.UTF_8))
                            callback(response, false)
                        } catch (e: Exception) {
                            Log.e("MqttService", "Error decoding message: ${e.message}")
                        }
                    }

                    Log.d("MqttService", "Subscribed to topic: $topic")
                } catch (e: MqttException) {
                    callback(null, true)
                    Log.e("MqttService", "Connection failed: ${e.message}")
                }
            }
        } catch (e: Exception) {
            callback(null, true)
            Log.e("MqttService", "Error initializing client: ${e.message}")
        }
    }

    companion object {
        val instance = MqttService()
    }
}