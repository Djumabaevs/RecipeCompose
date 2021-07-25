package com.djumabaevs.recipecompose.presentation.ui.recipeList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.djumabaevs.recipecompose.presentation.components.CircularIndeterminateProgressBar
import com.djumabaevs.recipecompose.presentation.components.RecipeCard
import com.djumabaevs.recipecompose.presentation.components.SearchAppBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    private val viewModel: RecipeListViewModel by viewModels()

    @ExperimentalComposeUiApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

//        viewModel.recipes.observe(viewLifecycleOwner, {recipes ->
//
//        })

        return ComposeView(requireContext()).apply {
            setContent {

                val recipes = viewModel.recipes.value

//                val query = remember { mutableStateOf("beef") }

                val query = viewModel.query.value

//                val _query = savedInstanceState { " beef" }

                val selectedCategory = viewModel.selectedCategory.value


                Column {
                    
                    SearchAppBar(
                        query = query,
                        onQueryChanged = viewModel::onQueryChanged,
                        onExecuteSearch = viewModel::newSearch,
                        scrollPosition = viewModel.categoryScrollPosition,
                        selectedCategory = selectedCategory,
                        onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged,
                        onChangeCategoryScrollPosition =
                        viewModel::onChangeCategoryScrollPosition) {
                        
                    }

                    CircularIndeterminateProgressBar(isDisplayed = true)
    
                    LazyColumn {
                        itemsIndexed(
                            items = recipes
                        ) { index, recipe ->
                            RecipeCard(recipe = recipe, onClick = {})

                        }
                    }
                }
            }
        }
    }
}


/*   for(recipe in recipes) {
       Log.d(TAG, "onCreateView: ${recipe.title}")
   }

   Column(modifier = Modifier.padding(16.dp)) {
       Text(
           text = "Recipe List",
           style = TextStyle(
               fontSize = 21.sp
           )
       )
       Spacer(modifier = Modifier.padding(10.dp))
       Button(
           onClick = {
               findNavController().navigate(R.id.viewRecipe)
           }
       ) {
           Text(text = "To Recipe Fragment")
       }
   }*/


/*        val view = inflater.inflate(R.layout.fragment_recipe_list, container, false)
        view.findViewById<ComposeView>(R.id.compose_view).setContent {

            Column(modifier = Modifier
                .border(border = BorderStroke(1.dp, Color.Black))
                .padding(16.dp)) {

                Text(text = "This is a composable inside a fragment")
                Spacer(modifier = Modifier.padding(16.dp))
                CircularProgressIndicator()
                Spacer(modifier = Modifier.padding(16.dp))
                Text(text = "Nice")
                Spacer(modifier = Modifier.padding(16.dp))

                val customView = HorizontalDottedProgress(LocalContext.current)
                AndroidView(factory = {customView}) {

                }
            }

        }*/


//Three other ways to use compose

/*   return ComposeView(requireContext()).apply {
       setContent {
           Text(text = "Recipe List Fragment")
       }
   }*/

/*
        val view = ComposeView(requireContext())
        view.apply {
            setContent { 
                Text(text = "Hello Compose")
            }
        }
        return view*/

/* val view = inflater.inflate(R.layout.fragment_recipe_list,
 container, false)
 return view*/


