package fr.jaetan.botiot.networking.models

import fr.jaetan.botiot.models.Game
import kotlinx.serialization.Serializable

@Serializable
data class GameResponse(
    val game: Game
)