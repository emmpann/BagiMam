package com.github.emmpann.bagimam.home

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.github.emmpann.bagimam.R
import com.github.emmpann.bagimam.databinding.FragmentHomeBinding
import java.util.*

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewPager: ViewPager2
    private lateinit var imageSliderAdapter: ImageSliderAdapter
    private val images =
        listOf(R.drawable.image1, R.drawable.image2, R.drawable.image3) // Your image resources

    private var currentPage = 0
    private var timer: Timer? = null
    private val handler = Handler()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set click listener for iv_Profile if it's found
        binding.ivProfile?.setOnClickListener {
            // Navigate to ProfileFragment
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }

        // Initialize ViewPager
        viewPager = binding.viewPager
        imageSliderAdapter = ImageSliderAdapter(requireContext(), images)
        viewPager.adapter = imageSliderAdapter
    }

    override fun onResume() {
        super.onResume()
        startAutoSlider()
    }

    override fun onPause() {
        super.onPause()
        stopAutoSlider()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun startAutoSlider() {
        val update = Runnable {
            if (currentPage == images.size) {
                currentPage = 0
            }
            viewPager.setCurrentItem(currentPage++, true)
        }

        timer = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                handler.post(update)
            }
        }, 3000, 3000) // Change second parameter to set delay between slides
    }

    private fun stopAutoSlider() {
        timer?.cancel()
    }
}
