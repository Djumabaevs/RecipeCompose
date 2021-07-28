package com.djumabaevs.recipecompose.presentation.components.util

import androidx.compose.material.ScaffoldState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

class SnackbarController(
    private val scope: CoroutineScope
) {
    private val snackbarJob: Job? = null

    fun getScope() = scope

    fun showSnackbar(
        scaffoldState: ScaffoldState,
        message: String,
        actionLabel: String,
    ) {

    }

}