package fr.jaetan.botiot.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import fr.jaetan.botiot.helper.GameController
import fr.jaetan.botiot.helper.NavigationRoutes
import fr.jaetan.botiot.models.Difficulties
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    fun createGame(difficulty: Difficulties, navController: NavHostController) {
        viewModelScope.launch(Dispatchers.IO) {
            GameController.instance.createGame(difficulty)
            launch(Dispatchers.Main) {
                navController.navigate(NavigationRoutes.game)
            }
        }
    }
}