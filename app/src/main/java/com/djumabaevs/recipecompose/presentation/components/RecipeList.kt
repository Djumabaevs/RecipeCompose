package com.djumabaevs.recipecompose.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.djumabaevs.recipecompose.presentation.components.util.ShimmerAnimation
import com.djumabaevs.recipecompose.presentation.ui.recipeList.PAGE_SIZE
import com.djumabaevs.recipecompose.presentation.ui.recipeList.RecipeListEvent

@Composable
fun RecipeList(

) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colors.background)) {
        if(loading) {  //&&recipes.isEmpty()

            Surface(color = MaterialTheme.colors.background) {
                LazyColumn {
                    repeat(5) {
                        item {
                            ShimmerAnimation()
                        }
                    }
                }
            }

        } else {
            LazyColumn {
                itemsIndexed(
                    items = recipes
                ) { index, recipe ->
                    viewModel.onChangeRecipeScrollPosition(index)

                    if((index + 1) >= (page * PAGE_SIZE) && !loading) {
                        viewModel.onTriggerEvent(RecipeListEvent.NewPageEvent)
                    }

                    RecipeCard(recipe = recipe, onClick = {})
                }
            }
        }
        CircularIndeterminateProgressBar(isDisplayed = loading)

        DefaultSnackbar(
            snackbarHostState = scaffoldState.snackbarHostState,
            onDismiss = {
                scaffoldState.snackbarHostState
                    .currentSnackbarData?.dismiss()
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}