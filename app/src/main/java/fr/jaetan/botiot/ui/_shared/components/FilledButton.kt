package fr.jaetan.botiot.ui._shared.components

import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import fr.jaetan.botiot.ui._shared.theme.SurfacePrimary
import fr.jaetan.botiot.ui._shared.theme.TextSecondary

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FilledButton(
    @StringRes title: Int,
    @StringRes subtitle: Int? = null,
    background: Color = SurfacePrimary,
    onPressColor: Color? = null,
    modifier: Modifier = Modifier,
    onCLick: () -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }
    val scaleAnimation by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        label = "scaleAnimation"
    )
    val backgroundColor by animateColorAsState(
        targetValue = if (isPressed && onPressColor != null) onPressColor else background,
        label = "backgroundColor"
    )

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .scale(scaleAnimation)
            .fillMaxWidth()
            .height(90.dp)
            .clip(CircleShape)
            .border(1.dp, Color.White, CircleShape)
            .background(backgroundColor)
            .pointerInput(Unit) {
                awaitEachGesture {
                    awaitFirstDown().also { it.consume() }
                    isPressed = true
                    val up = waitForUpOrCancellation()
                    up?.let {
                        it.consume()
                        onCLick()
                    }
                    isPressed = false
                }
            }
    ) {
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.headlineSmall
        )
        subtitle?.let {
            Text(
                text = stringResource(it),
                style = MaterialTheme.typography.labelLarge,
                color = TextSecondary
            )
        }
    }
}