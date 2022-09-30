package com.example.imdbapp.features.movies.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imdbapp.R
import com.example.imdbapp.features.movies.domain.model.TopMovies
import com.example.imdbapp.features.movies.ui.adapter.TopMoviesListAdapter
import com.example.imdbapp.features.movies.ui.viewmodel.TopMoviesViewModel
import kotlinx.android.synthetic.main.fragment_top_movies.*


class TopMoviesFragment : Fragment() {
    private lateinit var viewModel: TopMoviesViewModel
    private val listAdapter = TopMoviesListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_top_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(TopMoviesViewModel::class.java)
        viewModel.moviesDataList.observe(viewLifecycleOwner, moviesDataListDataObserver)
        viewModel.loading.observe(viewLifecycleOwner, loadingLiveDataObserver)
        viewModel.loadError.observe(viewLifecycleOwner, errorLiveDataObserver)
        viewModel.getData()

        moviesListTopMovies.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = listAdapter
        }
    }

    private val moviesDataListDataObserver = Observer<TopMovies> { list ->
        list?.let {
            moviesListTopMovies.visibility = View.VISIBLE
            it.items?.let { items ->
                listAdapter.updateTopList(items)
            }
        }
    }

    private val loadingLiveDataObserver = Observer<Boolean> { isLoading ->
        loadingViewTopMovies.visibility = if (isLoading) View.VISIBLE else View.GONE

        if (isLoading) {
            listErrorTopMovies.visibility = View.GONE
            moviesListTopMovies.visibility = View.GONE
        }
    }

    private val errorLiveDataObserver = Observer<Boolean> { isError ->
        listErrorTopMovies.visibility = if (isError) View.VISIBLE else View.GONE
    }
}