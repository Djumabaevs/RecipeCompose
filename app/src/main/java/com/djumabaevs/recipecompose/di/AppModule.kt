package com.djumabaevs.recipecompose.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
object AppModule {



}




//    @Singleton
//    @Provides
//    fun provideApplication(@ApplicationContext app: Context): BaseApplication {
//        return app as BaseApplication
//    }



