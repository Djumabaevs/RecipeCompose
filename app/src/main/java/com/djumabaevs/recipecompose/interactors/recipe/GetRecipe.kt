package com.djumabaevs.recipecompose.interactors.recipe

import com.djumabaevs.recipecompose.cache.RecipeDao
import com.djumabaevs.recipecompose.cache.model.RecipeEntityMapper
import com.djumabaevs.recipecompose.domain.data.DataState
import com.djumabaevs.recipecompose.domain.model.Recipe
import com.djumabaevs.recipecompose.network.RecipeService
import com.djumabaevs.recipecompose.network.model.RecipeDtoMapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Retrieve a recipe from the cache given it's unique id.
 */
class GetRecipe (
    private val recipeDao: RecipeDao,
    private val entityMapper: RecipeEntityMapper,
    private val recipeService: RecipeService,
    private val recipeDtoMapper: RecipeDtoMapper
        ) {
    fun execute(
    recipeId: Int,
    token: String,
    isNetworkAvailable: Boolean
    ): Flow<DataState<Recipe>> = flow {
        try{
            emit(DataState.loading())

            //just to show loading, cache is fast
            delay(1000)

            val recipe = getRecipeFromCache

        } catch (e: Exception) {
            emit(DataState.error<Recipe>(e.message?: "Unknown error"))
        }
    }

    private suspend fun getRecipeFromCache(recipeId: Int): Recipe? {
        return recipeDao.getRecipeById(recipeId)?.let { recipeEntity ->
            entityMapper.mapToDomainModel(recipeEntity)
        }
    }

    private suspend fun getRecipeFromNetwork(token: String, recipeId: Int): Recipe {
        return recipeDtoMapper.mapToDomainModel(recipeService.get(token, recipeId))
    }

}