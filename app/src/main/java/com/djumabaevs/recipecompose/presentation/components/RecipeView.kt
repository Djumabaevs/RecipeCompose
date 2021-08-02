package com.djumabaevs.recipecompose.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.djumabaevs.recipecompose.domain.model.Recipe

@Composable
fun RecipeView(
    recipe: Recipe
) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {

    }
}