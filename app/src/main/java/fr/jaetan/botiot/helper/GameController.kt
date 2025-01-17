package fr.jaetan.botiot.helper

import fr.jaetan.botiot.models.Difficulties
import fr.jaetan.botiot.models.Game
import fr.jaetan.botiot.models.MqttMessageType
import fr.jaetan.botiot.networking.GameApi
import fr.jaetan.botiot.networking.MqttService
import fr.jaetan.botiot.networking.models.MqttResponse
import kotlinx.coroutines.flow.MutableStateFlow

class GameController {
    var game = MutableStateFlow(null as Game?)
        private set

    suspend fun createGame(difficulty: Difficulties) {
        game.value = GameApi.createGame(difficulty)
    }

    suspend fun startGame(onError: () -> Unit) {
        game.value?.let {
            GameApi.startGame(it.id)
            MqttService.instance.connect(it.topic) { message, isError ->
                if (isError) onError()
                else if (message != null) updateGame(message)
            }
        }
    }

    private fun updateGame(response: MqttResponse) {
        when (MqttMessageType.fromInt(response.type)) {
            MqttMessageType.Action -> {
                if (response.action == null) return
                game.value = game.value?.copy(actions = game.value!!.actions.plus(response.action))
            }
            MqttMessageType.InfoAction -> {
                if (response.lives == null) return
                game.value = game.value?.copy(lives = response.lives)
                // TODO: update action status (success or not)
            }
            MqttMessageType.Final -> {
                if (response.win == null) return
                game.value = game.value?.copy(isWin = response.win)
            }
            else -> {}
        }
    }

    fun resetGame() {
        game.value = null
    }

    companion object {
        val instance = GameController()
    }
}