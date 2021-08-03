package com.djumabaevs.recipecompose.cache

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface RecipeDao {

    @Insert
    suspend fun insertRecipe(recipe: Re)

}