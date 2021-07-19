package com.djumabaevs.recipecompose.repository

import com.djumabaevs.recipecompose.domain.model.Recipe
import com.djumabaevs.recipecompose.domain.util.DomainMapper
import com.djumabaevs.recipecompose.network.RecipeService
import com.djumabaevs.recipecompose.network.model.RecipeDto
import com.djumabaevs.recipecompose.network.model.RecipeDtoMapper

class RecipeRepositoryImpl(
    private val recipeService: RecipeService,
    private val mapper: RecipeDtoMapper
//    private val mapper: DomainMapper<RecipeDto, Recipe>
): RecipeRepository {
    override suspend fun search(token: String, page: Int, query: String): List<Recipe> {
        val result = recipeService.search(token, page, query).recipes
        return mapper.toDomainList(result)
    }

    override suspend fun get(token: String, id: Int): Recipe {
        return mapper.mapToDomainModel(recipeService.get(token, id))
    }
}