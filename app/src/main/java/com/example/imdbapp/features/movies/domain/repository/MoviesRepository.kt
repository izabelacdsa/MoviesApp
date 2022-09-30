package com.example.imdbapp.features.movies.domain.repository

import android.app.Application
import com.example.imdbapp.data.network.ApiService
import com.example.imdbapp.di.NetworkModule
import com.example.imdbapp.di.component.DaggerApiComponent
import com.example.imdbapp.features.movies.domain.model.TopMovies
import io.reactivex.Single
import javax.inject.Inject

class MoviesRepository(application: Application) {
    @Inject
    lateinit var api: ApiService

    init {
        DaggerApiComponent.builder()
            .networkModule(NetworkModule(application))
            .build()
            .inject(this)
    }

    fun getTopMovies(apiKey: String): Single<TopMovies> {
        return api.getTopMovies(apiKey)
    }
}