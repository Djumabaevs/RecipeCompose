package com.djumabaevs.recipecompose.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.djumabaevs.recipecompose.domain.model.Recipe
import com.djumabaevs.recipecompose.util.DEFAULT_RECIPE_IMAGE
import com.djumabaevs.recipecompose.util.LoadPicture

@Composable
fun RecipeView(
    recipe: Recipe
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
    ) {
        items(50) {
            recipe.featuredImage?.let { url ->
                val image = LoadPicture(url = url, defaultImage = DEFAULT_RECIPE_IMAGE).value
                image?.let { img ->
                    Image(
                        bitmap = img.asImageBitmap(),
                        contentDescription = "Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(260.dp)
                    )
                }
            }
        }
    }
}