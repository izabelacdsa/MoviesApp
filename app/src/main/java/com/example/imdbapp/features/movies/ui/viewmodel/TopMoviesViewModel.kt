package com.example.imdbapp.features.movies.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.imdbapp.di.NetworkModule
import com.example.imdbapp.di.component.DaggerViewModelComponent
import com.example.imdbapp.features.movies.domain.model.TopMovies
import com.example.imdbapp.features.movies.domain.repository.MoviesRepository
import com.example.imdbapp.util.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TopMoviesViewModel(application: Application) : AndroidViewModel(application) {

    val moviesDataList by lazy { MutableLiveData<TopMovies>() }
    val loadError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }

    private val disposable = CompositeDisposable()

    @Inject
    lateinit var apiService: MoviesRepository

    fun getData() {
        DaggerViewModelComponent.builder()
            .networkModule(NetworkModule(getApplication()))
            .build()
            .inject(this)

        loading.value = true
        getTopMovies()
    }

    private fun getTopMovies() {
        disposable.add(
            apiService.getTopMovies(Constants.API_KEY)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<TopMovies>() {
                    override fun onSuccess(topMovie: TopMovies) {
                        loadError.value = false
                        moviesDataList.value = topMovie
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loading.value = false
                        moviesDataList.value = null
                        loadError.value = true
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}