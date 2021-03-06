package com.djumabaevs.recipecompose.di

import com.djumabaevs.recipecompose.cache.RecipeDao
import com.djumabaevs.recipecompose.cache.model.RecipeEntityMapper
import com.djumabaevs.recipecompose.interactors.recipe.GetRecipe
import com.djumabaevs.recipecompose.interactors.recipeList.RestoreRecipes
import com.djumabaevs.recipecompose.interactors.recipeList.SearchRecipes
import com.djumabaevs.recipecompose.network.RecipeService
import com.djumabaevs.recipecompose.network.model.RecipeDtoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object InteractorsModule {

    @ViewModelScoped
    @Provides
    fun provideSearchRecipe(
        recipeService: RecipeService,
        recipeDao: RecipeDao,
        recipeEntityMapper: RecipeEntityMapper,
        recipeDtoMapper: RecipeDtoMapper
    ): SearchRecipes {
        return SearchRecipes(
            recipeService = recipeService,
            recipeDao = recipeDao,
            entityMapper = recipeEntityMapper,
            dtoMapper = recipeDtoMapper
        )
    }

    @ViewModelScoped
    @Provides
    fun provideRestoreRecipes(
        recipeDao: RecipeDao,
        recipeEntityMapper: RecipeEntityMapper,
    ): RestoreRecipes {
        return RestoreRecipes(
            recipeDao = recipeDao,
            entityMapper = recipeEntityMapper,
        )
    }

    @ViewModelScoped
    @Provides
    fun provideGetRecipe(
        recipeDao: RecipeDao,
        recipeEntityMapper: RecipeEntityMapper,
        recipeService: RecipeService,
        recipeDtoMapper: RecipeDtoMapper,
    ): GetRecipe {
        return GetRecipe(
            recipeDao = recipeDao,
            entityMapper = recipeEntityMapper,
            recipeService = recipeService,
            recipeDtoMapper = recipeDtoMapper,
        )
    }
}