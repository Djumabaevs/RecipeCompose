package com.djumabaevs.recipecompose.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier.*


@Composable
fun CircularIndeterminateProgressBar(
    isDisplayed: Boolean,
) {
    if(isDisplayed) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(35.dp),
            horizontalArrangement = Arrangement.Center) {

            CircularProgressIndicator(
                color = MaterialTheme.colors.primary
            )
        }
    }
}