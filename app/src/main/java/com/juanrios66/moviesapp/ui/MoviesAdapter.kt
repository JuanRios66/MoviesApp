package com.juanrios66.moviesapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.juanrios66.moviesapp.R
import com.juanrios66.moviesapp.databinding.MovieListItemBinding
import com.juanrios66.moviesapp.model.Movie
import com.squareup.picasso.Picasso

class MoviesAdapter(
    private val onItemClicked: (Movie)-> Unit,
) : Adapter<MoviesAdapter.ViewHolder>() {

    private var listmovie: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listmovie[position])
        holder.itemView.setOnClickListener { onItemClicked(listmovie[position]) }
    }

    override fun getItemCount(): Int {
        return listmovie.size
    }

    fun appendItems(newItem: MutableList<Movie>) {
        listmovie.clear()
        listmovie.addAll(newItem)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = MovieListItemBinding.bind(view)
        private val context: Context = binding.root.context
        fun bind(movie: Movie) {
            with(binding) {
                titleText.text = movie.title
                releaseTextView.text = context.getString(R.string.release_info, movie.releaseDate)
                voteTextView.text = context.getString(R.string.vote_avg_info, movie.voteAverage.toString())
                if (movie.posterPath != null) {
                    Picasso.get().load("https://image.tmdb.org/t/p/w500" + movie.posterPath).into(pictureImageView)
                }
            }
        }
    }

}


