package com.github.emmpann.bagimam.splash

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.github.emmpann.bagimam.R
import org.koin.androidx.viewmodel.ext.android.viewModel


//@Suppress("DEPRECATION")
//class SplashFragment : Fragment() {
//    private val splashViewModel: SplashViewModel by viewModel()
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        Handler().postDelayed({
//
//            when (findNavController().currentDestination?.id) {
//                R.id.homeWorkerActivity -> {
//                    findNavController().navigate(R.id.action_splashFragment_to_homeWorkerActivity)
//                }
//
//                R.id.homeActivity -> {
//                    findNavController().navigate(R.id.action_splashFragment_to_homeActivity)
//                }
//
//                else -> {
//                    if (onBoardingFinished()) {
//                        splashViewModel.getSessionData().observe(viewLifecycleOwner) { user ->
//                            if (user.role == "worker") {
//                                findNavController().navigate(R.id.action_splashFragment_to_homeWorkerActivity)
//                            } else {
//                                findNavController().navigate(R.id.action_splashFragment_to_homeActivity)
//                            }
//                        }
//                    } else {
//                        findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
//                    }
//                }
//            }
//        }, 3000)
//
//        return inflater.inflate(R.layout.fragment_splash, container, false)
//    }
//
//    private fun onBoardingFinished(): Boolean {
//        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
//        return sharedPref.getBoolean("Finished", false)
//    }
//
//}