package com.imdb.movies.view.dashboard.favourite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.imdb.movies.R
import com.imdb.movies.adapter.FavMovieAdapter
import com.imdb.movies.adapter.MovieAdapter
import com.imdb.movies.databinding.FragmentFavouriteBinding
import com.imdb.movies.model.Movie
import com.imdb.movies.network.Status
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class FavouriteFragment : Fragment() {

    private var _binding: FragmentFavouriteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: FavouriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUI()
        binding.refreshLayout.also { it.post { loadMovies() } }.setOnRefreshListener {
            loadMovies()
        }
    }

    private fun setUI(movieList: List<Movie> = emptyList()) {
        binding.rvMovie.apply {
            if (adapter == null) {
                adapter = FavMovieAdapter(requireContext(), movieList.toMutableList(), ::onItemClick)
            } else {
                (adapter as FavMovieAdapter).apply {
                    movies.clear()
                    movies.addAll(movieList)
                    notifyDataSetChanged()
                }
            }
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

    private fun loadMovies() {
        lifecycleScope.launch {
            viewModel.getFavouriteMovies().collect{
                it.let { resource ->
                    when (resource.status) {
                        Status.LOADING -> {
                            binding.refreshLayout.isRefreshing = true
                        }
                        Status.SUCCESS -> {
                            binding.refreshLayout.isRefreshing = false
                            setUI(resource.response ?: emptyList())

                        }
                        Status.ERROR -> {
                            binding.refreshLayout.isRefreshing = false
                        }
                        else -> {

                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}