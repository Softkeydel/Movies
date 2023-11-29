package com.imdb.movies.view.dashboard.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.imdb.movies.R
import com.imdb.movies.adapter.MovieAdapter
import com.imdb.movies.databinding.FragmentHomeBinding
import com.imdb.movies.model.Movie

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvMovie.apply {
            adapter = MovieAdapter(requireContext(),
                 emptyList(), ::onItemClick)
        }
    }

    private fun onItemClick(view: View, position: Int, movie: Movie) {
        when(view.id) {
            R.id.btnFav -> {

            }
            else -> {
                findNavController().navigate(R.id.action_dashboardFragment_to_detailsFragment)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}