package com.djumabaevs.recipecompose.presentation.ui.recipeList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout

//        viewModel.recipes.observe(viewLifecycleOwner, {recipes ->
//
//        })


//                val _query = savedInstanceState { " beef" }

//                val query = remember { mutableStateOf("beef") }




/*  ShimmerRecipeCardItem(
      colors = listOf(
          Color.LightGray.copy(0.9f),
          Color.LightGray.copy(0.2f),
          Color.LightGray.copy(0.9f),
      ),
      cardHeight = 250.dp )*/



//                    GradientDemo()

/*                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        horizontalArrangement = Arrangement.Center
                            ){
                        val state = remember { mutableStateOf(IDLE) }

                        AnimatedHeartButton(
                            modifier = Modifier,
                            buttonState = state,
                            onToggle = {
                                state.value = if(state.value == IDLE) ACTIVE else IDLE
                            })

                    }


                    PulsingDemo()*/




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


@Composable
fun SimpleSnackbarDemo(
    show: Boolean,
    onHideSnackbar: () -> Unit,
){
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val snackbar = createRef()
        if(show){
            Snackbar(
                modifier = Modifier.constrainAs(snackbar) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                action = {
                    Text(
                        "Hide",
                        modifier = Modifier.clickable(onClick = onHideSnackbar),
                        style = MaterialTheme.typography.h5
                    )
                },
            ) {
                Text(text = "Hey look a snackbar")
            }
        }
    }
}