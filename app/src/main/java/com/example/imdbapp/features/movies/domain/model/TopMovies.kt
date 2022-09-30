package com.example.imdbapp.features.movies.domain.model

data class TopMovies(
    val items: List<TopMoviesItem>?,
    val errorMessage: String?
)