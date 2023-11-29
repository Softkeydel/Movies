package com.imdb.movies.view.splash

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.imdb.movies.R
import com.imdb.movies.base.AppClass
import com.imdb.movies.databinding.FragmentSplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AnimatorInflater.loadAnimator(requireContext(), R.animator.logo_anim)
            .also { it.setTarget(binding.imvLogo) }.start()

        lifecycleScope.launch(Dispatchers.Main) {
            delay(1000)
            findNavController().navigate(R.id.action_splashFragment_to_dashboardFragment)

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}