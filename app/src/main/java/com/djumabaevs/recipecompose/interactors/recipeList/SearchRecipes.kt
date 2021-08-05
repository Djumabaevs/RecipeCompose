package com.djumabaevs.recipecompose.interactors.recipeList

import com.djumabaevs.recipecompose.cache.RecipeDao
import com.djumabaevs.recipecompose.cache.model.RecipeEntityMapper
import com.djumabaevs.recipecompose.domain.data.DataState
import com.djumabaevs.recipecompose.domain.model.Recipe
import com.djumabaevs.recipecompose.network.RecipeService
import com.djumabaevs.recipecompose.network.model.RecipeDtoMapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRecipes(
    private val recipeDao: RecipeDao,
    private val recipeService: RecipeService,
    private val entityMapper: RecipeEntityMapper,
    private val dtoMapper: RecipeDtoMapper
) {
    fun execute(
        query: String,
        page: Int,
        token: String,
        isNetworkAvailable: Boolean
    ): Flow<DataState<List<Recipe>>> = flow {
        try {
            emit(DataState.loading())

            //just to show pagination, api is fast
            delay(1000)

            //force error for testing
            if(query == "error") {
                throw Exception("Search failed.")
            }
            //if there is a network connection
            if(isNetworkAvailable) {
                //Convert:NetworkRecipeEntity -> Entity -> RecipeCacheEntity
            }
        }

    }

    private suspend fun getRecipesFromNetwork(
        token: String,
        page: Int,
        query: String
    ): List<Recipe> {
        return dtoMapper.toDomainList(
            recipeService.search(
                query = query,
                page = page,
                token = token
            ).recipes
        )
    }

}