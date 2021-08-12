package com.djumabaevs.recipecompose.presentation.ui.recipe

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.djumabaevs.recipecompose.domain.model.Recipe
import com.djumabaevs.recipecompose.interactors.recipe.GetRecipe
import com.djumabaevs.recipecompose.presentation.util.ConnectivityManager
import com.djumabaevs.recipecompose.presentation.util.DialogQueue
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named

const val STATE_KEY_RECIPE = "recipe.state.recipe.key"

@HiltViewModel
class RecipeDetailViewModel
@Inject constructor(
    private val getRecipe: GetRecipe,
    private val connectivityManager: ConnectivityManager,
    @Named("auth_token") private val token: String,
    private val state: SavedStateHandle
) : ViewModel() {

    val recipe: MutableState<Recipe?> = mutableStateOf(null)
    val loading = mutableStateOf(false)
    val onLoad: MutableState<Boolean> = mutableStateOf(false)
    val dialogQueue = DialogQueue()

    init {
        //restore if process dies
        state.get<Int>(STATE_KEY_RECIPE)?.let {recipeId ->
            onTriggerEvent(RecipeEvent.GetRecipeEvent(recipeId))
        }
    }
}