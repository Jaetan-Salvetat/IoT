package fr.jaetan.botiot.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import fr.jaetan.botiot.R
import fr.jaetan.botiot.models.Difficulties
import fr.jaetan.botiot.ui._shared.components.AppScaffold
import fr.jaetan.botiot.ui._shared.components.FilledButton
import fr.jaetan.botiot.ui._shared.theme.Green
import fr.jaetan.botiot.ui._shared.theme.Orange
import fr.jaetan.botiot.ui._shared.theme.Red

@Composable
fun HomeView(navController: NavHostController, viewModel: HomeViewModel = viewModel()) {
    AppScaffold {
        Content(viewModel, navController)
    }
}

@Composable
private fun Content(viewModel: HomeViewModel, navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        FilledButton(
            title = R.string.easy_uppercase,
            subtitle = R.string.easy_subtitle,
            onPressColor = Green,
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp)
        ) {
            viewModel.createGame(Difficulties.Easy, navController)
        }
        FilledButton(
            title = R.string.medium_uppercase,
            subtitle = R.string.medium_subtitle,
            onPressColor = Orange,
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp)
        ) {
            viewModel.createGame(Difficulties.Medium, navController)
        }
        FilledButton(
            title = R.string.hard_uppercase,
            subtitle = R.string.hard_subtitle,
            onPressColor = Red,
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp)
        ) {
            viewModel.createGame(Difficulties.Hard, navController)
        }
    }
}