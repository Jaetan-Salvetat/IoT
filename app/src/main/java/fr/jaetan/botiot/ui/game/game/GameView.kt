package fr.jaetan.botiot.ui.game.game

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import fr.jaetan.botiot.R
import fr.jaetan.botiot.helper.GameController
import fr.jaetan.botiot.helper.NavigationRoutes
import fr.jaetan.botiot.ui._shared.components.AppScaffold
import fr.jaetan.botiot.ui._shared.theme.Red

@Composable
fun GameView(navController: NavHostController, viewModel: GameViewModel = viewModel()) {
    val game by GameController.instance.game.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.initialize()
    }

    LaunchedEffect(game) {
        if (viewModel.job == null && game?.actions?.isNotEmpty() == true) viewModel.startTimerWithMilliseconds()
        if (game?.isWin != null) {
            viewModel.job?.cancel()
            game?.timer = viewModel.timerString
            navController.navigate(NavigationRoutes.stats) {
                popUpTo(NavigationRoutes.home)
            }
        }
    }

    AppScaffold {
        if (viewModel.isLoading || game?.actions?.isEmpty() == true) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            Content(viewModel)
        }
    }

    if (viewModel.isError) {
        AlertDialog(
            onDismissRequest = { navController.popBackStack() },
            title = { Text(stringResource(R.string.error)) },
            text = { Text(stringResource(R.string.error_as_occured)) },
            confirmButton = {
                TextButton(onClick = { navController.popBackStack() }) {
                    Text(stringResource(R.string.back))
                }
            }
        )
    }
}

@Composable
private fun Content(viewModel: GameViewModel) {
    val game by GameController.instance.game.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.weight(1f))
        Text(viewModel.timerString, style = MaterialTheme.typography.displayLarge)
        Row {
            for (live in 1..(game?.lives ?: 1)) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = null,
                    tint = Red
                )
            }
        }
        Spacer(Modifier.height(30.dp))
        game?.actions?.lastOrNull()?.let {
            Text(it.label, style = MaterialTheme.typography.displaySmall, textAlign = TextAlign.Center)
        }
        Spacer(Modifier.weight(1f))
    }
}