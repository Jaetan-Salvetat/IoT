package fr.jaetan.botiot.networking.models

import kotlinx.serialization.Serializable

@Serializable
data class MqttResponse(
    val type: Int,
    val action: Action? = null,
    val success: Boolean? = null,
    val win: Boolean? = null,
    val lives: Int? = null
)

@Serializable
enum class ActionStatus (val value: Int) {
    OK(1),
    KO(0),
    NA(2)
}

@Serializable
data class Action(
    val id: Int,
    val label: String,
    val isActivated: Boolean,
    val actionStatus: ActionStatus
)