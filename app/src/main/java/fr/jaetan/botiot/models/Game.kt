package fr.jaetan.botiot.models

import fr.jaetan.botiot.networking.models.Action
import kotlinx.serialization.Serializable

@Serializable
data class Game(
    val id: Int,
    val difficulty: Difficulty,
    val topic: String,
    var lives: Int,
    val timerLimit: Int,
    val finalStatus: Int,
    val actualStatus: Int,
    val actions: List<Action>,
    var isWin: Boolean? = null,
    var timer: String? = null

)