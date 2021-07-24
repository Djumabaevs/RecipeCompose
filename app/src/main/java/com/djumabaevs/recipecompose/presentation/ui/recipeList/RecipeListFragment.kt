package com.djumabaevs.recipecompose.presentation.ui.recipeList

import android.accessibilityservice.AccessibilityService
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.djumabaevs.recipecompose.presentation.components.FoodCategoryChip
import com.djumabaevs.recipecompose.presentation.components.RecipeCard
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

                    Surface(
                        modifier = Modifier
                            .fillMaxWidth(),
                        elevation = 8.dp,
                        color = Color.White
                    ) {

                        Column {

                            Row(modifier = Modifier.fillMaxWidth()) {

//                            val focusManager = LocalFocusManager.current
                                val keyboardController = LocalSoftwareKeyboardController.current
                                TextField(
                                    value = query,
                                    modifier = Modifier
                                        .fillMaxWidth(0.9f)
                                        .padding(8.dp),
                                    onValueChange = { newValue ->
                                        viewModel.onQueryChanged(newValue)
                                    },
                                    label = {
                                        Text(text = "Search")
                                    },
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Text,
                                        imeAction = ImeAction.Search
                                    ),
                                    leadingIcon = {
                                        Icon(Icons.Filled.Search, contentDescription = "icon")
                                    },
                                    keyboardActions = KeyboardActions(
                                        onSearch = {
                                            viewModel.newSearch()
                                        },
                                        onDone = {
//                                        focusManager.clearFocus()
                                            keyboardController?.hideSoftwareKeyboard()
                                        }
                                    ),
                                    textStyle = TextStyle(
                                        color = MaterialTheme.colors.onSurface,
                                    ),
                                    colors = TextFieldDefaults.textFieldColors(
                                        backgroundColor = MaterialTheme.colors.surface,
                                    )
                                )
//                            Spacer(modifier = Modifier.padding(10.dp))
                            }

                            LazyRow(modifier = Modifier.fillMaxWidth()) {
                                items(50) {
                                    for(category in getAllFoodCategories()) {
                                        FoodCategoryChip(
                                            category = category.value,
                                            isSelected = selectedCategory == category,
                                            onSelectedCategoryChanged = {
                                                viewModel.onSelectedCategoryChanged(it)
                                            },
                                            onExecuteSearch = {
                                                viewModel::newSearch
//                                                viewModel.onQueryChanged(it)
//                                                viewModel.newSearch(it)
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
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


