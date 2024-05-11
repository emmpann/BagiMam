package com.github.emmpann.bagimam.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.github.emmpann.bagimam.onboarding.screens.FirstOnboardingFragment
import com.github.emmpann.bagimam.onboarding.screens.SecondOnboardingFragment
import com.github.emmpann.bagimam.onboarding.screens.ThirdOnboardingFragment
import com.github.emmpann.bagimam.R


class ViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_view_pager, container, false)
        val fragmentList = arrayListOf(
            FirstOnboardingFragment(),
            SecondOnboardingFragment(),
            ThirdOnboardingFragment()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )
        val viewPager: ViewPager2 = view.findViewById(R.id.viewPager)
        viewPager.adapter = adapter

        return view
    }

}