package com.djumabaevs.recipecompose.interactors.recipeList

import com.djumabaevs.recipecompose.cache.RecipeDao
import com.djumabaevs.recipecompose.cache.model.RecipeEntityMapper
import com.djumabaevs.recipecompose.domain.data.DataState
import com.djumabaevs.recipecompose.domain.model.Recipe
import com.djumabaevs.recipecompose.network.RecipeService
import com.djumabaevs.recipecompose.network.model.RecipeDtoMapper
import com.djumabaevs.recipecompose.util.RECIPE_PAGINATION_PAGE_SIZE
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
                val recipes = getRecipesFromNetwork(
                    query = query,
                    page = page,
                    token = token
                )
                //insert into cache
                recipeDao.insertRecipes(entityMapper.toEntityList(recipes))
            }
            //query the cache
            val cacheResult = if(query.isBlank()) {
                recipeDao.getAllRecipes(
                    page = page,
                    pageSize = RECIPE_PAGINATION_PAGE_SIZE
                )
            } else {
                recipeDao.searchRecipes(
                    query = query,
                    pageSize = RECIPE_PAGINATION_PAGE_SIZE,
                    page = page
                )
            }
            //emit List<Recipe> from cache
            val list = entityMapper.fromEntityList(cacheResult)
            emit(DataState.success(list))
        } catch (e: Exception) {
            emit(DataState.error<List<Recipe>>(e.message?: "Unknown error"))
        }

    }

    //WARING: this will throw exception if there is no network connection.
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