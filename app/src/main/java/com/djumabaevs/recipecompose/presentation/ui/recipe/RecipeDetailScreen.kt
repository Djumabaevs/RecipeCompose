package com.djumabaevs.recipecompose.presentation.ui.recipe

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.djumabaevs.recipecompose.presentation.components.IMAGE_HEIGHT
import com.djumabaevs.recipecompose.presentation.components.LoadingRecipeListShimmer
import com.djumabaevs.recipecompose.presentation.components.RecipeView
import com.djumabaevs.recipecompose.presentation.theme.AppTheme

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
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

        val loading = viewModel.loading.value
        val recipe = viewModel.recipe.value
        val dialogQueue = viewModel.dialogQueue
        val scaffoldState = rememberScaffoldState()

        AppTheme(
            darkTheme = isDarkTheme,
            isNetworkAvailable = isNetworkAvailable,
            displayProgressBar = loading,
            scaffoldState = scaffoldState,
            dialogQueue = dialogQueue.queue.value
        ) {
            Scaffold(
                scaffoldState = scaffoldState,
                snackbarHost = {
                    scaffoldState.snackbarHostState
                }
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    if(loading && recipe == null) {
                        LoadingRecipeListShimmer(imageHeight = IMAGE_HEIGHT.dp)
                    } else if(!loading && recipe == null && onLoad) {
                        TODO("show invalid recipe")
                    } else {
                        recipe?.let{ RecipeView(recipe = it)}
                    }
                }

            }
        }
    }
}