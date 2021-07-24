package com.djumabaevs.recipecompose.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.djumabaevs.recipecompose.presentation.ui.recipeList.FoodCategory
import com.djumabaevs.recipecompose.presentation.ui.recipeList.getAllFoodCategories
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@Composable
fun SearchAppBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onExecuteSearch: () -> Unit,
    scrollPosition: Int,
    selectedCategory: FoodCategory?,
    onSelectedCategoryChanged: (String) -> Unit,
    onChangeCategoryScrollPosition: (Int) -> Unit,


) {

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
                        onQueryChanged(newValue)
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
                            onExecuteSearch()
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

            val scrollState = rememberLazyListState()
            val scope = rememberCoroutineScope()

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, bottom = 8.dp),
                state = scrollState
            ) {
                items(50) {
                    scope.launch {
                        scrollState
                            .scrollToItem(
                                scrollPosition)
                    }


                    for(category in getAllFoodCategories()) {
                        FoodCategoryChip(
                            category = category.value,
                            isSelected = selectedCategory == category,
                            onSelectedCategoryChanged = {
                                onSelectedCategoryChanged(it)
                                onChangeCategoryScrollPosition(
                                    scrollState.firstVisibleItemIndex
                                )
                            },
                            onExecuteSearch = {
                                onExecuteSearch()
//                                                viewModel.onQueryChanged(it)
//                                                viewModel.newSearch(it)
                            }
                        )
                    }
                }
            }
        }
    }
}