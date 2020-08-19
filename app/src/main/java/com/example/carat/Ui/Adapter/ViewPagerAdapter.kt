package com.example.carat.Ui.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.carat.Ui.Fragment.ProfileFragment

class ViewPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                ProfileFragment()
            }
            else -> {
                ProfileFragment()
            }
        }
    }

    override fun getCount(): Int = 2
}