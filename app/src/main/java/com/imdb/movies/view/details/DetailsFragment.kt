package com.imdb.movies.view.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.imdb.movies.R
import com.imdb.movies.databinding.FragmentDetailsBinding
import com.imdb.movies.model.Movie
import com.imdb.movies.util.loadImage


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: DetailsViewModel by viewModels()

    private val args by navArgs<DetailsFragmentArgs>()



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        binding.movie = args.movie


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imvMovie.loadImage(args.movie.image?.imageUrl)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}