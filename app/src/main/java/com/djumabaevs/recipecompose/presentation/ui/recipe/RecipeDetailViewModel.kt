package com.djumabaevs.recipecompose.presentation.ui.recipe

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.djumabaevs.recipecompose.interactors.recipe.GetRecipe
import com.djumabaevs.recipecompose.presentation.util.ConnectivityManager
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


}