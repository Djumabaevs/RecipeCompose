package com.djumabaevs.recipecompose.presentation.ui.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.djumabaevs.recipecompose.presentation.BaseApplication
import com.djumabaevs.recipecompose.presentation.components.CircularIndeterminateProgressBar
import com.djumabaevs.recipecompose.presentation.components.RecipeView
import com.djumabaevs.recipecompose.presentation.components.util.SnackbarController
import com.djumabaevs.recipecompose.presentation.theme.AppTheme
import com.djumabaevs.recipecompose.presentation.ui.recipe.RecipeEvent.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class RecipeFragment: Fragment() {

//    private var recipeId: MutableState<Int> = mutableStateOf(-1)
    @Inject
    lateinit var application: BaseApplication

    private val snackbarController = SnackbarController(lifecycleScope)

    private val viewModel: RecipeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.getInt("recipeId")?.let { recipeId ->
            viewModel.onTriggerEvent(GetRecipeEvent(recipeId))
        }

/*        CoroutineScope(Main).launch {
            delay(1000)
            arguments?.getInt("recipeId")?.let { rId ->
                recipeId.value = rId
            }
        }*/
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val loading = viewModel.loading.value
                val recipe = viewModel.recipe.value
                val scaffoldState = rememberScaffoldState()

                AppTheme(darkTheme = application.isDark.value) {
                    Scaffold(
                        scaffoldState = scaffoldState,
                        snackbarHost = {
                            scaffoldState.snackbarHostState
                        }
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            if(loading && recipe == null) {
                                Text("Loading...")
                            } else {
                                recipe?.let{
                                    if(it.id == 1) {
                                        snackbarController.showSnackbar(
                                            scaffoldState = scaffoldState,
                                            message = "An error occurred with this recipe.",
                                            actionLabel = "OK"
                                        )
                                    } else {
                                        RecipeView(recipe = it)
                                    }
                                }
                            }
                            CircularIndeterminateProgressBar(
                                isDisplayed = loading)
                        }

                    }
                }








/*                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
//                        text = recipeId.let{"Selected recipe: ${recipeId}"} ?: "Loading...",
                        *//*text = if(recipeId.value != -1) {
                            "Selected recipe: ${recipeId.value}"
                        } else {
                            "Loading ..."
                        }*//*
                        text =  recipe?.let {
                           "Selected recipe title: ${recipe.title}"
                        }?: "Loading..."
                        ,
                        style = TextStyle(
                            fontSize = 21.sp
                        )
                    )
                }*/
            }
        }
    }
}