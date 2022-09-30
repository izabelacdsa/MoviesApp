package com.example.imdbapp

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class BaseApplication(val app: Application) {

    @Provides
    fun provideApp(): Application = app
}