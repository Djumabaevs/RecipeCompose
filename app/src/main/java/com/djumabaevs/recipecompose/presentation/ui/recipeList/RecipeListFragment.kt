package com.djumabaevs.recipecompose.presentation.ui.recipeList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.djumabaevs.recipecompose.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeListFragment: Fragment() {

    val viewModel: RecipeListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("RecipeListFragment: ${viewModel}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setContent {
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
                }
            }
        }



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


    }

}