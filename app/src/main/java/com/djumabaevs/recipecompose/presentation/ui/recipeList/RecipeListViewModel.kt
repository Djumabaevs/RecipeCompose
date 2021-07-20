package com.djumabaevs.recipecompose.presentation.ui.recipeList

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.djumabaevs.recipecompose.domain.model.Recipe
import com.djumabaevs.recipecompose.repository.RecipeRepository
import javax.inject.Named


class RecipeListViewModel @ViewModelInject constructor(
    private val repository: RecipeRepository,
    private @Named("auth_token") val token: String
): ViewModel() {

    private val _recipes: MutableLiveData<List<Recipe>> = MutableLiveData()
    val recipes: LiveData<List<Recipe>> get() = _recipes



}