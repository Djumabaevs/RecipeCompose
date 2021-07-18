package com.djumabaevs.recipecompose.network.responses

import com.djumabaevs.recipecompose.network.model.RecipeDto
import com.google.gson.annotations.SerializedName

data class RecipeSearchResponse (
    @SerializedName("count")
    var count: Int,

    @SerializedName("results")
    var recipes: List<RecipeDto>
        )