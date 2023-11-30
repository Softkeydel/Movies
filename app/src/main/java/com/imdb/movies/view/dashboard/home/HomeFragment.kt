package com.imdb.movies.view.dashboard.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.imdb.movies.R
import com.imdb.movies.adapter.MovieAdapter
import com.imdb.movies.databinding.FragmentHomeBinding
import com.imdb.movies.model.Movie
import com.imdb.movies.network.Status
import kotlinx.coroutines.launch
import org.json.JSONObject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
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
                adapter = MovieAdapter(requireContext(), movieList.toMutableList(), ::onItemClick)
            } else {
                (adapter as MovieAdapter).apply {
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
                if(movie.favourite) {
                    viewModel.addToFavourite(movie)
                } else {
                    viewModel.removeFromFavourite(movie)
                }
            }
            else -> {
                findNavController().navigate(R.id.action_dashboardFragment_to_detailsFragment)

            }
        }
    }



    private fun loadMovies() {
        lifecycleScope.launch {
            viewModel.getMovies(JSONObject()).collect{
                it.let { resource ->
                    when (resource.status) {
                        Status.LOADING -> {
                            binding.refreshLayout.isRefreshing = true
                        }
                        Status.SUCCESS -> {
                            binding.refreshLayout.isRefreshing = false
                            setUI(resource.response?.data?.list?: emptyList())
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