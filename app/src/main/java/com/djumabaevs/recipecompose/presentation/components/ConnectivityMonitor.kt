package com.djumabaevs.recipecompose.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ConnectivityMonitor(
    isNetworkAvailable: Boolean
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "No network available!",
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.h6
        )
    }
}