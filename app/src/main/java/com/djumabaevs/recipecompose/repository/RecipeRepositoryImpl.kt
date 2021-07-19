package com.djumabaevs.recipecompose.repository

import com.djumabaevs.recipecompose.domain.model.Recipe

class RecipeRepositoryImpl: RecipeRepository {
    override suspend fun search(token: String, page: Int, query: String): List<Recipe> {

    }

    override suspend fun get(token: String, id: Int): Recipe {

    }
}