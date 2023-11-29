package com.imdb.movies.view.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.imdb.movies.R
import com.imdb.movies.adapter.ViewPagerFragmentAdapter
import com.imdb.movies.databinding.FragmentDashboardBinding
import com.imdb.movies.view.dashboard.favourite.FavouriteFragment
import com.imdb.movies.view.dashboard.home.HomeFragment


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
//        val adapter = ViewPagerFragmentAdapter(requireActivity())
        val adapter = ViewPagerFragmentAdapter(childFragmentManager, lifecycle)
        adapter.addFragment(HomeFragment())
        adapter.addFragment(FavouriteFragment())
        binding.viewPager.adapter = adapter
        binding.viewPager.isUserInputEnabled = false

        binding.bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    binding.viewPager.currentItem = 0
                }
                R.id.nav_favourite -> {
                    binding.viewPager.currentItem = 1
                }
            }
            return@setOnItemSelectedListener true
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}