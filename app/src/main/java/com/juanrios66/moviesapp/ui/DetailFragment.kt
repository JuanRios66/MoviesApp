package com.juanrios66.moviesapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.juanrios66.moviesapp.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso


class DetailFragment : Fragment() {

    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

        with(binding){
            val movie = args.movie
            titleTextview.text = movie.title
            descripcionTextView.text = movie.overview
            if(movie.posterPath != null){
                Picasso.get().load("https://image.tmdb.org/t/p/w500" + movie.posterPath)
                    .into(imageView)
            }

        }


        return root
    }
}