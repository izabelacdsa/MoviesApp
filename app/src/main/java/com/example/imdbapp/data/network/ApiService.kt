package com.example.imdbapp.data.network

import com.example.imdbapp.features.movies.domain.model.TopMovies
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("Top250Movies/{apiKey}")
    fun getTopMovies(
        @Path("apiKey") apiKey: String
    ): Single<TopMovies>
}