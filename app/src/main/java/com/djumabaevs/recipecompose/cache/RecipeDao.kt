package com.djumabaevs.recipecompose.cache

import androidx.room.Dao
import androidx.room.Insert
import com.djumabaevs.recipecompose.cache.model.RecipeEntity

@Dao
interface RecipeDao {

    @Insert
    suspend fun insertRecipe(recipe: RecipeEntity): Long

    @Insert
    suspend fun insertRecipes(recipes: List<RecipeEntity>): LongArray



}