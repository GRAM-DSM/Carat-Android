package com.example.carat.Ui.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carat.Presenter.Profile.ProfileContract
import com.example.carat.Presenter.Profile.ProfilePresenter
import com.example.carat.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.layout_profile_tab.view.*

class ProfileFragment : Fragment(), ProfileContract.View {
    private val profilePresenter: ProfilePresenter by lazy {
        ProfilePresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        profilePresenter.getProfileInfo()
        setProfileCarat()
        return view
    }

    override fun setProfileInfo() {

    }

    private fun setProfileCarat() {
        val tab = profile_tab_include.tabLayout_carat
        tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val pos = tab!!.position
//                val recyclerView = profile_tab_include.tabLayout_show_recyclerView
//                recyclerView.adapter = if (pos == 0) {
//
//                } else if(pos == 1) {
//
//                }
            }
        })
    }
}