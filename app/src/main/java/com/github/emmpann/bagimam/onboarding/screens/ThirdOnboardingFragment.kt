package com.github.emmpann.bagimam.onboarding.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.github.emmpann.bagimam.R
import com.github.emmpann.bagimam.onboarding.OnBoardingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ThirdOnboardingFragment : Fragment() {

    private val viewModel: OnBoardingViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_third_onboarding, container, false)
        val btnFinish: TextView = view.findViewById(R.id.tv_start)
        btnFinish.setOnClickListener {
//            findNavController().navigate(R.id.action_viewPagerFragment_to_registerFragment)
            viewModel.setUserFirstTime(false)
        }

        return view
    }
}