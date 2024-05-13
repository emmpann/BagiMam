package com.github.emmpann.bagimam.home

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.github.emmpann.bagimam.R
import com.github.emmpann.bagimam.databinding.FragmentHomeBinding
import com.github.emmpann.bagimam.donation.DonationFragmentDirections
import com.github.emmpann.core.domain.model.Orphanage
import com.github.emmpann.core.domain.model.Response
import com.github.emmpann.core.ui.DonationAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var donationAdapter: DonationAdapter

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
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObserver()
        setupRecyclerView()


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

    private fun setupObserver() {
//        viewModel.orphanageState.observe(viewLifecycleOwner) {
//            binding.tvName.text = String.format(getString(R.string.home_welcome), it)
//        }

        viewModel.orphanageState.observe(viewLifecycleOwner) {
            when (it) {
                is Response.Success -> {
                    donationAdapter.submitList(it.data)
                    showLoading(false)
                }

                is Response.Loading -> {
                    showLoading(true)
                }

                is Response.Failure -> {
                    showLoading(false)
                }

                else -> {

                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvBerbagi.overScrollMode = View.OVER_SCROLL_NEVER
        binding.rvBerbagi.layoutManager = LinearLayoutManager(requireContext())
        donationAdapter = DonationAdapter()
        binding.rvBerbagi.adapter = donationAdapter
        donationAdapter.setOnItemClickCallback(object : DonationAdapter.OnItemClickCallback {
            override fun onItemClicked(orphanage: Orphanage) {
                val toDetailDonation =
                    HomeFragmentDirections.actionHomeFragmentToDetailFragment()
                toDetailDonation.name = orphanage.name
                findNavController().navigate(toDetailDonation)
            }
        })
    }

    private fun showLoading(isShow: Boolean) {
        binding.progressBar.visibility = if (isShow) View.VISIBLE else View.GONE
    }
}
