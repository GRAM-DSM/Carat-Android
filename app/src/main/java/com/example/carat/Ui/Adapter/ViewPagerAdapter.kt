package com.example.carat.Ui.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.carat.R
import com.example.carat.Ui.Fragment.ProfileFragment
import com.example.carat.Ui.Fragment.TimeLineFragment

class ViewPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                TimeLineFragment()
            }
            1 -> {
                ProfileFragment()
            }
            else -> {
                TimeLineFragment()
            }
        }

    }

    override fun getCount(): Int = 2
}