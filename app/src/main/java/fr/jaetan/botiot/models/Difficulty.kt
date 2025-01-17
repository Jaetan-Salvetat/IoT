package fr.jaetan.botiot.models

import kotlinx.serialization.Serializable

@Serializable
data class Difficulty(
    val id: Int,
    val label: String,
    val lives: Int,
    val timerLimit: Int,
    val games: List<String>
)

enum class Difficulties(val id: Int) {
    Easy(0),
    Medium(1),
    Hard(2)
}