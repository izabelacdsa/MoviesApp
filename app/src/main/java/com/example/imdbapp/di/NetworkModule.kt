package com.example.imdbapp.di

import android.app.Application
import com.example.imdbapp.data.network.ApiService
import com.example.imdbapp.features.movies.domain.repository.MoviesRepository
import com.example.imdbapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
open class NetworkModule(val app: Application) {

    @Provides
    fun provideApi(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    open fun provideApiService(): MoviesRepository {
        return MoviesRepository(app)
    }
}