package fr.jaetan.botiot.ui.game.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.jaetan.botiot.helper.GameController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameViewModel: ViewModel() {
    var job: Job? = null
        private set
    var isLoading by mutableStateOf(false)
        private set
    var timerString by mutableStateOf("")
        private set
    var isError by mutableStateOf(false)

    fun initialize() {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading = true
            GameController.instance.startGame { isError = true }
            isLoading = false
        }
    }

    fun startTimerWithMilliseconds() {
         job = viewModelScope.launch(Dispatchers.Unconfined) {
            val startTime = System.currentTimeMillis() // Temps de départ
            while (true) {
                val elapsedTime = System.currentTimeMillis() - startTime
                val seconds = elapsedTime / 1000
                val milliseconds = elapsedTime % 1000
                timerString = "$seconds.${milliseconds / 10}"
                delay(25L)
            }
        }
    }
}