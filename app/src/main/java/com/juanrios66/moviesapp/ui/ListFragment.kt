package com.juanrios66.moviesapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.juanrios66.moviesapp.databinding.FragmentListBinding
import com.juanrios66.moviesapp.model.Movie
import com.juanrios66.moviesapp.model.MoviesList
import com.juanrios66.moviesapp.server.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? =  null
    private val binding get() = _binding!!
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        moviesAdapter = MoviesAdapter(onItemClicked = {onMovieItemClicked(it)})

        binding.moviesRecyclerView.apply{
            layoutManager = LinearLayoutManager(this@ListFragment.context)
            adapter = moviesAdapter
            setHasFixedSize(false)
        }
        loadMovies()
        return root
    }

    private fun onMovieItemClicked(movie: Movie) {
        findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment(movie = movie))
    }

    private fun loadMovies(){
        val apiKey = "8af62114ac80561969b9412f1d0e9dd4"

        ApiService.create()
            .getTopRated(apiKey)
            .enqueue(object : Callback<MoviesList>{
                override fun onResponse(call: Call<MoviesList>, response: Response<MoviesList>) {
                    if(response.isSuccessful) {
                        val listmovies: MutableList<Movie> = response.body()?.movies as MutableList<Movie>
                        moviesAdapter.appendItems(listmovies)
                    }
                }

                override fun onFailure(call: Call<MoviesList>, t: Throwable) {
                    Log.d("Error", t.message.toString())
                }

            })
    }

}