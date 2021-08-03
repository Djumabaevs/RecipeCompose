package com.djumabaevs.recipecompose.presentation.ui.recipeList

import SearchAppBar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.djumabaevs.recipecompose.presentation.BaseApplication
import com.djumabaevs.recipecompose.presentation.components.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import com.djumabaevs.recipecompose.presentation.components.HeartAnimationDefinition.HeartButtonState.*
import com.djumabaevs.recipecompose.presentation.components.util.ShimmerAnimation
import com.djumabaevs.recipecompose.presentation.components.util.SnackbarController
import com.djumabaevs.recipecompose.presentation.theme.AppTheme
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.djumabaevs.recipecompose.presentation.ui.recipeList.RecipeListEvent.*

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    @Inject
    lateinit var application: BaseApplication

    private val viewModel: RecipeListViewModel by viewModels()

    private val snackbarController = SnackbarController(lifecycleScope)

    @ExperimentalMaterialApi
    @ExperimentalComposeUiApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {

                val recipes = viewModel.recipes.value
                val query = viewModel.query.value
                val selectedCategory = viewModel.selectedCategory.value
                val loading = viewModel.loading.value
                val scaffoldState = rememberScaffoldState()
                val page = viewModel.page.value

                AppTheme(
                    darkTheme = application.isDark.value,
                    displayProgressbar = loading,
                    scaffoldState = scaffoldState
                ) {
                    Scaffold (
                        topBar = {
                            SearchAppBar(
                                query = query,
                                onQueryChanged = viewModel::onQueryChanged,
                                onExecuteSearch = {
                                    if(viewModel.selectedCategory.value?.value == "Milk") {
                                        snackbarController.getScope().launch {
                                            snackbarController.showSnackbar(
                                                scaffoldState = scaffoldState,
                                                message = "Invalid category: Milk!",
                                                actionLabel = "Hide",
                                            )
                                        }
                                    } else  {
                                        viewModel.onTriggerEvent(NewSearchEvent)
                                    }
                                },
                                categories = getAllFoodCategories(),
                                selectedCategory = selectedCategory,
                                onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged,
                                onToggleTheme = application::toggleLightTheme

                            )
                        },
//                        bottomBar = {
//                                    MyBottomBar()
//                        },
//                        drawerContent = {
//                            MyDrawer()
//                        },
                        scaffoldState = scaffoldState,
                        snackbarHost = {
                            scaffoldState.snackbarHostState
                        }
                            ) {
                            RecipeList(
                                loading = loading,
                                recipes = recipes,
                                onChangeRecipeScrollPosition = viewModel::onChangeRecipeScrollPosition,
                                page = page,
                                onTriggerEvent = {
                                    viewModel.onTriggerEvent(NewPageEvent)
                                },
                                scaffoldState = scaffoldState       ,
                                snackbarController = snackbarController,
                                navController = findNavController()
                            )
                    }
                }
            }
        }
    }
}

@Composable
fun GradientDemo(
) {

    val colors = listOf(
        Color.Blue,
        Color.Red,
        Color.Blue
    )
    val brush = Brush.linearGradient(
        colors,
        Offset(200f, 200f),
        Offset(400f, 400f),

    )
    Surface(shape = MaterialTheme.shapes.small) {
        Spacer(modifier = Modifier
            .fillMaxSize()
            .background(brush = brush))

    }
}

@Composable
fun MyBottomBar() {
    BottomNavigation(
        elevation = 12.dp
    ) {
        BottomNavigationItem(
            icon = {Icon(Icons.Default.BrokenImage, "brokenImage")},
            selected = false ,
            onClick = {}
        )
        BottomNavigationItem(
            icon = {Icon(Icons.Default.Search, "search")},
            selected = true ,
            onClick = {}
        )
        BottomNavigationItem(
            icon = {Icon(Icons.Default.AccountBalanceWallet, "wallet")},
            selected = false ,
            onClick = {}
        )
    }
}

@Composable
fun MyDrawer(
//    navController: NavController
) {
    Column {
        Text(text = "item1")
        Text(text = "item2")
        Text(text = "item3")
        Text(text = "item4")
        Text(text = "item5")

    }
}













