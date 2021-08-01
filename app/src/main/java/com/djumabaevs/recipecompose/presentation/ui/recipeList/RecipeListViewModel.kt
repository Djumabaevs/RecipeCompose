package com.djumabaevs.recipecompose.presentation.ui.recipeList

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.djumabaevs.recipecompose.domain.model.Recipe
import com.djumabaevs.recipecompose.repository.RecipeRepository
import com.djumabaevs.recipecompose.util.TAG
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Named
import com.djumabaevs.recipecompose.presentation.ui.recipeList.RecipeListEvent.*

const val PAGE_SIZE = 30

const val STATE_KEY_PAGE = "recipe.state.page.key"
const val STATE_KEY_QUERY = "recipe.state.query.key"
const val STATE_KEY_LIST_POSITION = "recipe.state.query.list_position"
const val STATE_KEY_SELECTED_CATEGORY = "recipe.state.query.selected_category"

class RecipeListViewModel @ViewModelInject constructor(
    private val repository: RecipeRepository,
    @Named("auth_token") private val token: String,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {

    val recipes: MutableState<List<Recipe>> = mutableStateOf(listOf())
    val query = mutableStateOf("")
    val selectedCategory: MutableState<FoodCategory?> = mutableStateOf(null)
    var categoryScrollPosition: Int = 0
    val loading = mutableStateOf(false)
    val page = mutableStateOf(1)
    private var recipeListScrollPosition = 0


//    private val _recipes: MutableLiveData<List<Recipe>> = MutableLiveData()
//    val recipes: LiveData<List<Recipe>> get() = _recipes

    init {

        savedStateHandle.get<Int>(STATE_KEY_PAGE)?.let { p ->
            setPage(p)
        }
        savedStateHandle.get<String>(STATE_KEY_QUERY)?.let { q ->
            setQuery(q)
        }
        savedStateHandle.get<Int>(STATE_KEY_LIST_POSITION)?.let { p ->
            setListScrollPosition(p)
        }
        savedStateHandle.get<FoodCategory>(STATE_KEY_SELECTED_CATEGORY)?.let {c ->
            setSelectedCategory(c)
        }

        onTriggerEvent(NewSearchEvent)
    }

    fun onTriggerEvent(event: RecipeListEvent) {
        viewModelScope.launch { 
            try {
                when(event) {
                    is NewSearchEvent -> {
                        newSearch()
                    }
                    is NewPageEvent -> {
                        nextPage()
                    }
                    is RestoreStateEvent -> {
                        restoreState()
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "onTriggerEvent: Exception: ${e}, ${e.cause}")
            }
        }
    }

    private suspend fun restoreState() {
        loading.value = true
        val results: MutableList<Recipe> = mutableListOf()
        for(p in 1..page.value) {
            val result = repository.search(
                token = token,
                page = p,
                query = query.value
            )
            results.addAll(result)
        }
    }

    //usecase 1
     private suspend fun newSearch() {
            loading.value = true
            delay(2000)
            val result = repository.search(
                token = token,
                page = 1,
                query = query.value
            )
            recipes.value = result
            loading.value = false
    }
    //usecase 2
    private suspend fun nextPage() {
            //prevent duplicate events due to recompose happening to quickly
            if((recipeListScrollPosition + 1) >= (page.value * PAGE_SIZE)) {
                loading.value = true
                incrementPage()
                Log.d(TAG, "nextPage: triggered: ${page.value}")
            //just to show pagination, api is fast
                delay(1000)

                if(page.value > 1) {
                    val result = repository.search(
                        token = token,
                        page = page.value,
                        query = query.value
                    )
                    Log.d(TAG, "nextPage: ${result}")
                    appendRecipes(result)
                }
                loading.value = false
            }
    }

    private fun appendRecipes(recipes: List<Recipe>) {
        val current = ArrayList(this.recipes.value)
        current.addAll(recipes)
        this.recipes.value = current
    }

    private fun incrementPage() {
        setPage(page.value + 1)
    }

    fun onChangeRecipeScrollPosition(position: Int) {
        setListScrollPosition(position = position)
    }

    private fun clearSelectedCategory() {
        setSelectedCategory(null)
        selectedCategory.value = null
    }

    private fun resetSearchState() {
        recipes.value = listOf()
        page.value = 1
        onChangeRecipeScrollPosition(0)
        if(selectedCategory.value?.value != query.value)
            clearSelectedCategory()
    }

    fun onQueryChanged(query: String) {
        setQuery(query)
    }

    fun onSelectedCategoryChanged(category: String) {
        val newCategory = getFoodCategory(category)
        setSelectedCategory(newCategory)
        onQueryChanged(category)
    }

    fun onChangeCategoryScrollPosition(position: Int) {
        categoryScrollPosition = position
    }

    private fun setListScrollPosition(position: Int) {
        recipeListScrollPosition = position
        savedStateHandle.set(STATE_KEY_LIST_POSITION, position)
    }

    private fun setPage(page: Int) {
        this.page.value = page
        savedStateHandle.set(STATE_KEY_PAGE, page)
    }

    private fun setSelectedCategory(category: FoodCategory?) {
        selectedCategory.value = category
        savedStateHandle.set(STATE_KEY_SELECTED_CATEGORY, category)
    }

    private fun setQuery(query: String) {
        this.query.value = query
        savedStateHandle.set(STATE_KEY_QUERY, query)
    }
}