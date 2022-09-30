package com.example.imdbapp.di.component

import com.example.imdbapp.di.NetworkModule
import com.example.imdbapp.features.movies.domain.repository.MoviesRepository
import dagger.Component

@Component(modules = [NetworkModule::class])
interface ApiComponent {
    fun inject(movieRepository: MoviesRepository)
}