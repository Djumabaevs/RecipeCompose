package com.djumabaevs.recipecompose.presentation.components.util

import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.runtime.Composable

object ShimmerAnimationDefinitions {

    enum class AnimationState {
        START, END
    }
}

@Composable
fun ShimmerAnimation() {
    val transition = rememberInfiniteTransition()
}