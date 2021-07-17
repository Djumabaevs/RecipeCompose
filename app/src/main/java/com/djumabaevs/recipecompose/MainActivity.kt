package com.djumabaevs.recipecompose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
            LazyColumn(modifier = Modifier.background(color = Color(0xFFf2f2f2))) {
                items(2) {
                    Image(
                        painterResource(
                            id = R.drawable.happy_meal
                        ),
                        modifier = Modifier.height(300.dp),

                        contentScale = ContentScale.Crop,
                        contentDescription = "meal picture"
                    )

                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Happy Meal",
                            style = TextStyle(
                                fontSize = 26.sp
                            )
                        )
                        Spacer(modifier = Modifier.padding(top = 10.dp))
                        Text(
                            text = "800 calories",
                            style = TextStyle(
                                fontSize = 17.sp
                            )
                        )
                        Spacer(modifier = Modifier.padding(top = 10.dp))
                        Text(
                            text = "$5.99",
                            style = TextStyle(
                                color = Color(0xFF85bb65),
                                fontSize = 17.sp
                            )
                        )
                    }
                }
            }
        }
    }
}