package com.djumabaevs.recipecompose.presentation.ui.recipeList

sealed class RecipeListEvent {

    object NewSearchEvent: RecipeListEvent()
    object NewPageEvent: RecipeListEvent()
}
