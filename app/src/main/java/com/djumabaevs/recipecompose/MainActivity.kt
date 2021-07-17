package com.djumabaevs.recipecompose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

                    LazyColumn(modifier = Modifier
                        .background(color = Color(0xFFf2f2f2))
                        .fillMaxSize()) {
                   items(1) {
                       Image(
                           painterResource(
                               id = R.drawable.happy_meal
                           ),
                           modifier = Modifier.height(300.dp),

                           contentScale = ContentScale.Crop,
                           contentDescription = "meal picture"
                       )

                       Column(modifier = Modifier.padding(16.dp)) {
                           Row(
                               modifier = Modifier.fillMaxWidth(),
                               horizontalArrangement = Arrangement.SpaceBetween
                           ) {
                               Text(
                                   text = "Happy Meal",
                                   style = TextStyle(
                                       fontSize = 26.sp
                                   )
                               )
                               Text(
                                   text = "$5.99",
                                   style = TextStyle(
                                       color = Color(0xFF85bb65),
                                       fontSize = 17.sp
                                   )
                               )

                           }

                           Spacer(modifier = Modifier.padding(top = 10.dp))
                           Text(
                               text = "800 calories",
                               style = TextStyle(
                                   fontSize = 17.sp
                               )
                           )
                           Spacer(modifier = Modifier.padding(top = 10.dp))
                       }
                   }
               }


           /* Column {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .border(border = BorderStroke(width = 1.dp, color = Color.Black)),
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = "ITEM1",
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = "ITEM2",
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                }

                Spacer(modifier = Modifier.padding(20.dp))

                Row(
                    modifier = Modifier
                        .width(200.dp)
                        .height(200.dp)
                        .border(border = BorderStroke(width = 1.dp, color = Color.Black)),
                    horizontalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = "ITEM1",
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )

                }
            }*/



        }
    }
}