package com.djumabaevs.recipecompose.presentation.components.util

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object ShimmerAnimationDefinitions {

    enum class AnimationState {
        START, END
    }

    val ShimmerColorShades = listOf(

        Color.LightGray.copy(0.9f),

        Color.LightGray.copy(0.2f),

        Color.LightGray.copy(0.9f)

    )
}

@Composable
fun ShimmerAnimation() {
    val transition = rememberInfiniteTransition()

    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            tween(1200, easing = FastOutSlowInEasing),
            RepeatMode.Restart
        )
    )
    val brush = Brush.linearGradient(
        colors = ShimmerColorShades,
        start = Offset(10f, 10f),
        end = Offset(translateAnim, translateAnim)
    )

    ShimmerItem(brush = brush)
}

@Composable
fun ShimmerItem(
    brush: Brush
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .size(250.dp)
                .background(brush = brush))
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .padding(vertical = 8.dp)
            .background(brush = brush))

    }

}