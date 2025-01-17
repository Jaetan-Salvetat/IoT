package fr.jaetan.botiot.networking.models

import kotlinx.serialization.Serializable

@Serializable
data class CreateGameRequest(
    val difficulty: Int,
    val topic: String = "BopIoT-953"
)
