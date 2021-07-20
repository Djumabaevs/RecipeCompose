package com.djumabaevs.recipecompose.presentation.ui.recipeList

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.djumabaevs.recipecompose.repository.RecipeRepository
import javax.inject.Named


class RecipeListViewModel @ViewModelInject constructor(
    private val randomString: String,
    private val repository: RecipeRepository,
    private @Named("auth_token") val token: String
): ViewModel() {

    init {
        println("ViewModel is $randomString")
        println("ViewModel is $repository")
        println("ViewModel is $token")
    }
}