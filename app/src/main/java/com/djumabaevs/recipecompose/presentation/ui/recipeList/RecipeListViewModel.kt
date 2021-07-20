package com.djumabaevs.recipecompose.presentation.ui.recipeList

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.djumabaevs.recipecompose.repository.RecipeRepository


class RecipeListViewModel @ViewModelInject constructor(
    private val randomString: String,
    private val repository: RecipeRepository,
    private val token: String
): ViewModel() {

    init {
        println("ViewModel is $randomString")
    }
}