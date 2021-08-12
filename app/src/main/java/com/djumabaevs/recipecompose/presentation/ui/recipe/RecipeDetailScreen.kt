package com.djumabaevs.recipecompose.presentation.ui.recipe

import androidx.compose.runtime.Composable

@Composable
fun RecipeDetailScreen(
    isDarkTheme: Boolean,
    isNetworkAvailable: Boolean,
    recipeId: Int?,
    viewModel: RecipeDetailViewModel
) {
    if(recipeId == null) {
        TODO("show invalid recipe")
    } else {
        // fire a one-off event to get the recipe from api
        val onLoad = viewModel.onLoad.value
        if(!onLoad) {
            viewModel.onLoad.value = true
            viewModel.onTriggerEvent(RecipeEvent.GetRecipeEvent(recipeId))
        }
    }
}