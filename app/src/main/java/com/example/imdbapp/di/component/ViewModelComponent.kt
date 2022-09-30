package com.example.imdbapp.di.component

import com.example.imdbapp.BaseApplication
import com.example.imdbapp.di.NetworkModule
import com.example.imdbapp.features.movies.ui.viewmodel.TopMoviesViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        BaseApplication::class
    ]
)
interface ViewModelComponent {
    fun inject(viewModel: TopMoviesViewModel)
}