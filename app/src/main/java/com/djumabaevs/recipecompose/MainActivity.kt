package com.djumabaevs.recipecompose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Column {
                Image(painterResource(
                    id = R.drawable.happy_meal), 
                    contentDescription = "meal picture" )
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Happy Meal")
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    Text(text = "800 calories")
                }
            }
            
        }


    }
}