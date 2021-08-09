package com.djumabaevs.recipecompose.presentation.util

import android.app.Application
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConnectivityManager
@Inject constructor(application: Application)
{
    private val connectionLiveData = ConnectionLiveData(application)
}