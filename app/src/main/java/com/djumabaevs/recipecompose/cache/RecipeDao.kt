package com.djumabaevs.recipecompose.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.djumabaevs.recipecompose.cache.model.RecipeEntity

@Dao
interface RecipeDao {

    @Insert
    suspend fun insertRecipe(recipe: RecipeEntity): Long

    @Insert
    suspend fun insertRecipes(recipes: List<RecipeEntity>): LongArray

    @Query("SELECT * FROM recipes WHERE id = :id")
    suspend fun getRecipeById(id: Int): RecipeEntity?

    @Query("DELETE FROM recipes WHERE id IN (:ids)")
    suspend fun deleteRecipes(ids: List<Int>): Int

    @Query("DELETE FROM recipes")
    suspend fun deleteAllRecipes()

    @Query("DELETE FROM recipes WHERE id = :primaryKey")
    suspend fun deleteRecipe(primaryKey: Int): Int

}