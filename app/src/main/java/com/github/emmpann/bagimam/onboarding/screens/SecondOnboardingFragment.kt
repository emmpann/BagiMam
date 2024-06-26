package com.github.emmpann.bagimam.onboarding.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.github.emmpann.bagimam.R

class SecondOnboardingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_second_onboarding, container, false)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
        val btnNext: TextView = view.findViewById(R.id.tv_next)
        btnNext.setOnClickListener {
            viewPager?.currentItem = 2
        }
        return view
    }

}