package com.djumabaevs.recipecompose.presentation.components.util

import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

object ShimmerAnimationDefinitions {

    enum class AnimationState {
        START, END
    }
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
}