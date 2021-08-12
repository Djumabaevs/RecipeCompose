package com.djumabaevs.recipecompose.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.*
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.djumabaevs.recipecompose.R
import com.djumabaevs.recipecompose.datastore.SettingsDataStore
import com.djumabaevs.recipecompose.presentation.navigation.Screen
import com.djumabaevs.recipecompose.presentation.ui.recipe.RecipeDetailScreen
import com.djumabaevs.recipecompose.presentation.ui.recipe.RecipeDetailViewModel
import com.djumabaevs.recipecompose.presentation.ui.recipeList.RecipeListScreen
import com.djumabaevs.recipecompose.presentation.ui.recipeList.RecipeListViewModel
import com.djumabaevs.recipecompose.presentation.util.ConnectivityManager
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.internal.lifecycle.HiltViewModelFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var connectivityManager: ConnectivityManager
    @Inject
    lateinit var settingsDataStore:SettingsDataStore

    override fun onStart() {
        super.onStart()
        connectivityManager.registerConnectionObserver(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        connectivityManager.unregisterConnectionObserver(this)
    }

    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Screen.RecipeList.route) {
                composable(route = Screen.RecipeList.route) { navBackStackEntry ->
                    val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
                    val viewModel: RecipeListViewModel = viewModel("RecipeListViewModel", factory)
                    RecipeListScreen(
                        isDarkTheme = settingsDataStore.isDark.value,
                        isNetworkAvailable = connectivityManager.isNetworkAvailable.value,
                        onToggleTheme = settingsDataStore::toggleTheme,
                        onNavigateToRecipeDetailScreen = navController::navigate,
                        viewModel = viewModel,
                    )
                }
                composable(
                    route = Screen.RecipeDetail.route + "/{recipeId}",
                    arguments = listOf(navArgument("recipeId") {
                        type = NavType.IntType
                    })
                ) { navBackStackEntry ->
                    val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
                    val viewModel: RecipeDetailViewModel = viewModel("RecipeDetailViewModel", factory)
                    RecipeDetailScreen(
                        isDarkTheme = settingsDataStore.isDark.value,
                        isNetworkAvailable = connectivityManager.isNetworkAvailable.value,
                        recipeId = navBackStackEntry.arguments?.getInt("recipeId"),
                        viewModel = viewModel,
                    )
                }
            }
        }

    }
}




    /*    val service = Retrofit.Builder()
            .baseUrl("https://food2fork.ca/api/recipe/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(RecipeService::class.java)

        CoroutineScope(IO).launch {
            val responseRecipe = service.get(
                token = "Token 9c8b06d329136da358c2d00e76946b0111ce2c48",
                id = 583
            )
            Log.d("Main", "onCreate: ${responseRecipe.title}")
        }*/



      /*  val mapper = RecipeNetworkMapper()
        val recipe = Recipe()
        val networkEntity: RecipeNetworkEntity = mapper.mapToEntity(recipe)
        val r: Recipe = mapper.mapFromEntity(networkEntity)*/


       /* supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, RecipeListFragment())
            .commit()*/


class Extension {


           public fun NavGraphBuilder.composable(
               route: String,
               arguments: List<NamedNavArgument> = emptyList(),
               deepLinks: List<NavDeepLink> = emptyList(),
               content: @Composable (NavBackStackEntry) -> Unit
           ) {
               addDestination(
                   ComposeNavigator.Destination(provider[ComposeNavigator::class], content).apply {
                       this.route = route
                       arguments.forEach { (argumentName, argument) ->
                           addArgument(argumentName, argument)
                       }
                       deepLinks.forEach { deepLink ->
                           addDeepLink(deepLink)
                       }
                   }
               )
           }
       }