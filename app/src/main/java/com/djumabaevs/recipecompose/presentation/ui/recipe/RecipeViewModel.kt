
package com.djumabaevs.recipecompose.presentation.ui.recipe
/*
import android.text.GetChars
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.djumabaevs.recipecompose.domain.model.Recipe
import com.djumabaevs.recipecompose.presentation.ui.recipe.RecipeEvent.*
import com.djumabaevs.recipecompose.repository.RecipeRepository
import com.djumabaevs.recipecompose.util.TAG
import kotlinx.coroutines.launch
import javax.inject.Named

const val STATE_KEY_RECIPE = "state.key.recipeId"

class RecipeViewModel
@ViewModelInject constructor(
    private val recipeRepository: RecipeRepository,
    @Named("auth_token") private val token: String,
    @Assisted private val state: SavedStateHandle
): ViewModel()
{
    val recipe: MutableState<Recipe?> = mutableStateOf(null)
    val loading = mutableStateOf(false)

    init {
        state.get<Int>(STATE_KEY_RECIPE)?.let { recipeId ->
            onTriggerEvent(GetRecipeEvent(recipeId))
        }
    }

    fun onTriggerEvent(event: RecipeEvent) {
        viewModelScope.launch {

            try {
                when(event) {
                    is GetRecipeEvent -> {
                        if(recipe.value == null) {
                            getRecipe(event.id)
                        }
                    }
                }

            } catch (e: Exception) {
                Log.e(TAG, "onTriggerEvent: Exception: ${e}, ${e.cause}")
            }
        }
    }

    private suspend fun getRecipe(id: Int) {
        loading.value = true
    //simulate a delay to show loading
        val recipe = recipeRepository.get(token = token, id = id)
        this.recipe.value = recipe

        state.set(STATE_KEY_RECIPE, recipe.id)
        loading.value = false
    }

}
*/
