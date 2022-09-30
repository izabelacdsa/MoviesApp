package com.example.imdbapp.features.movies.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbapp.R
import com.example.imdbapp.databinding.ItemTopMoviesBinding
import com.example.imdbapp.features.movies.domain.model.TopMoviesItem

class TopMoviesListAdapter(private val topMoviesList: ArrayList<TopMoviesItem>) :
    RecyclerView.Adapter<TopMoviesListAdapter.TopMoviesViewHolder>() {

    fun updateTopList(newTopList: List<TopMoviesItem>) {
        topMoviesList.clear()
        topMoviesList.addAll(newTopList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMoviesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemTopMoviesBinding>(
            inflater,
            R.layout.item_top_movies,
            parent,
            false
        )
        return TopMoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopMoviesViewHolder, position: Int) {
        val topMovies = topMoviesList[position]
        holder.view.topMovies = topMovies
    }

    override fun getItemCount() = topMoviesList.size

    class TopMoviesViewHolder(var view: ItemTopMoviesBinding) : RecyclerView.ViewHolder(view.root)

}