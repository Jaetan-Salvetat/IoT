package fr.jaetan.botiot.ui.game.stats

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import fr.jaetan.botiot.R
import fr.jaetan.botiot.helper.GameController
import fr.jaetan.botiot.ui._shared.components.AppScaffold
import fr.jaetan.botiot.ui._shared.components.FilledButton

@Composable
fun StatsView(navController: NavHostController, viewModel: StatsViewModel = viewModel()) {
    AppScaffold {
        Content(navController)
    }
}

@Composable
private fun Content(navController: NavHostController) {
    val game by GameController.instance.game.collectAsState()
    if (game == null) return

    val titleTextStyle = MaterialTheme.typography.titleLarge.copy(
        textDecoration = TextDecoration.Underline
    )

    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp).padding(top = 140.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.results), style = MaterialTheme.typography.displayMedium)
        Spacer(Modifier.height(70.dp))
        Row {
            Column(horizontalAlignment = AbsoluteAlignment.Right, modifier = Modifier.padding(end = 20.dp)) {
                Text("Nombre de niveau :", style = titleTextStyle)
                Text("Difficulté :", style = titleTextStyle)
                Text("Temps total :", style = titleTextStyle)
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(game?.actions?.size.toString(), style = MaterialTheme.typography.titleLarge)
                Text(game?.difficulty?.label ?: "", style = MaterialTheme.typography.titleLarge)
                Text(game?.timer ?: "", style = MaterialTheme.typography.titleLarge)
            }
        }

        Spacer(Modifier.weight(1f))

        FilledButton(title = R.string.back_to_home, modifier = Modifier.padding(bottom = 40.dp)) {
            navController.popBackStack()
        }
    }
}