package com.djumabaevs.recipecompose.presentation.ui.util

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.djumabaevs.recipecompose.presentation.components.GenericDialogInfo
import java.util.*

class DialogQueue {

    //Queue for first-in first-out behaviour
    val queue: MutableState<Queue<GenericDialogInfo>> = mutableStateOf(LinkedList())
}