package com.djumabaevs.recipecompose.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.djumabaevs.recipecompose.domain.model.Recipe
import com.djumabaevs.recipecompose.presentation.navigation.Screen
import com.djumabaevs.recipecompose.presentation.ui.recipeList.PAGE_SIZE
import kotlinx.coroutines.ExperimentalCoroutinesApi


/*
import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.djumabaevs.recipecompose.R
import com.djumabaevs.recipecompose.domain.model.Recipe
import com.djumabaevs.recipecompose.presentation.components.util.ShimmerAnimation
import com.djumabaevs.recipecompose.presentation.components.util.SnackbarController
import com.djumabaevs.recipecompose.presentation.ui.recipeList.PAGE_SIZE
import com.djumabaevs.recipecompose.presentation.ui.recipeList.RecipeListEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@Composable
fun RecipeList(
    loading: Boolean,
    recipes: List<Recipe>,
    onChangeRecipeScrollPosition: (Int) -> Unit,
    page: Int,
    onTriggerEvent: (RecipeListEvent) -> Unit,
    scaffoldState: ScaffoldState,
    snackbarController: SnackbarController,
    navController: NavController
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
                    onChangeRecipeScrollPosition(index)

                    if((index + 1) >= (page * PAGE_SIZE) && !loading) {
                        onTriggerEvent(RecipeListEvent.NewPageEvent)
                    }

                    RecipeCard(
                        recipe = recipe,
                        onClick = {
                            if(recipe.id != null) {
                                val bundle = Bundle()
                                bundle.putInt("recipeId", recipe.id!!)
                                navController.navigate(R.id.viewRecipe, bundle)

                            } else {
                                snackbarController.getScope().launch {
                                    snackbarController.showSnackbar(
                                        scaffoldState = scaffoldState,
                                        message = "Recipe Error",
                                        actionLabel = "OK"
                                    )
                                }
                            }
                        })
                }
            }
        }

    }
}*/

@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun RecipeList(
    loading: Boolean,
    recipes: List<Recipe>,
    onChangeScrollPosition: (Int) -> Unit,
    page: Int,
    onTriggerNextPage: () -> Unit,
    onNavigateToRecipeDetailScreen: (String) -> Unit,
){
    Box(modifier = Modifier
        .background(color = MaterialTheme.colors.surface)
    ) {
        if (loading && recipes.isEmpty()) {
            LoadingRecipeListShimmer(imageHeight = 250.dp,)
        }
        else if(recipes.isEmpty()){
            NothingHere()
        }
        else {
            LazyColumn{
                itemsIndexed(
                    items = recipes
                ) { index, recipe ->
                    onChangeScrollPosition(index)
                    if ((index + 1) >= (page * PAGE_SIZE) && !loading) {
                        onTriggerNextPage()
                    }
                    RecipeCard(
                        recipe = recipe,
                        onClick = {
                            val route = Screen.RecipeDetail.route + "/${recipe.id}"
                            onNavigateToRecipeDetailScreen(route)
                        }
                    )
                }
            }
        }
    }
}