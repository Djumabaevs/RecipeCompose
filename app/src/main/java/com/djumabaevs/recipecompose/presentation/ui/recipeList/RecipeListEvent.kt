package com.djumabaevs.recipecompose.presentation.ui.recipeList

sealed class RecipeListEvent {

    object NewSearchEvent: RecipeListEvent()
    object NewPageEvent: RecipeListEvent()

    //restore after process death
    object RestoreStateEvent: RecipeListEvent()
}
