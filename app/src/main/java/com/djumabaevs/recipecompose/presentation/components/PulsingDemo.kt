package com.djumabaevs.recipecompose.presentation.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp

object PulseAnimationDefinitions {

    enum class PulseState {
        INITIAL, FINAL
    }
}

@Composable
fun PulsingDemo() {
    val color = MaterialTheme.colors.primary
    val infiniteTransition = rememberInfiniteTransition()
    val pulseState = infiniteTransition.animateFloat(
        initialValue = 40f,
        targetValue = 50f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 500,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Restart
        )
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            imageVector = Icons.Default.Favorite,
            modifier = Modifier.align(CenterVertically)
                .height(pulseState.value.dp)
                .width(pulseState.value.dp),
            contentDescription = null
        )
    }

    Canvas(modifier = Modifier
        .fillMaxWidth()
        .height(55.dp)) {
        drawCircle(
            radius = pulseState.value,
            brush = SolidColor(color)
        )
    }
}