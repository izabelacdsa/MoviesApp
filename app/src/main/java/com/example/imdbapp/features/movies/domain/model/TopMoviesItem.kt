package com.example.imdbapp.features.movies.domain.model

data class TopMoviesItem(
    val id: String?,
    val rank: String?,
    val title: String?,
    val fullTitle: String?,
    val year: String?,
    val image: String?,
    val crew: String?,
    val imDbRating: String?,
    val imDbRatingCount: String?
)