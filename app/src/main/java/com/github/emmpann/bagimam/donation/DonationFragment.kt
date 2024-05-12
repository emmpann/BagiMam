package com.github.emmpann.bagimam.donation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.emmpann.bagimam.R
import com.github.emmpann.bagimam.databinding.FragmentDonationBinding
import com.github.emmpann.core.domain.model.Orphanage
import com.github.emmpann.core.domain.model.Response
import com.github.emmpann.core.ui.DonationAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DonationFragment : Fragment() {

    private val viewModel: DonationViewModel by viewModel()
    private lateinit var binding: FragmentDonationBinding
    private lateinit var donationAdapter: DonationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDonationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObserver()
        setupRecyclerView()
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
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvList.overScrollMode = View.OVER_SCROLL_NEVER
        binding.rvList.layoutManager = LinearLayoutManager(requireContext())
        donationAdapter = DonationAdapter()
        binding.rvList.adapter = donationAdapter
        donationAdapter.setOnItemClickCallback(object : DonationAdapter.OnItemClickCallback {
            override fun onItemClicked(orphanage: Orphanage) {
//                val toDetailArticle =
//                    HomeFragmentDirections.actionHomeFragmentToDetailArticleFragment()
//                toDetailArticle.articleSlug = article.slug
//                findNavController().navigate(toDetailArticle)
            }
        })

//        binding.rvToday.layoutManager =
//        LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
//        bannerAdapter = BannerAdapter()
//        binding.rvToday.adapter = bannerAdapter
//        bannerAdapter.setOnItemClickCallback(object : BannerAdapter.OnItemClickCallback {
//            override fun onItemClicked(article: Article) {
//                val toDetailArticle =
//                    HomeFragmentDirections.actionHomeFragmentToDetailArticleFragment()
//                toDetailArticle.articleSlug = article.slug
//                findNavController().navigate(toDetailArticle)
//            }
//        })
//
//        autoScrollJob = CoroutineScope(Dispatchers.Main).launch {
//            while (true) {
//                delay(delay)
//                if (currentPosition == bannerAdapter.itemCount) {
//                    currentPosition = 0
//                }
//                binding.rvToday.smoothScrollToPosition(++currentPosition)
//            }
//        }
    }

    private fun showLoading(isShow: Boolean) {
        binding.progressBar.visibility = if (isShow) View.VISIBLE else View.GONE
    }
}