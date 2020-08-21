package com.example.carat.Ui.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.carat.Model.UserData
import com.example.carat.Presenter.Profile.ProfileContract
import com.example.carat.Presenter.Profile.ProfilePresenter
import com.example.carat.R
import com.example.carat.Ui.Activity.ChangeProfile
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.layout_profile_info.view.*
import kotlinx.android.synthetic.main.layout_profile_tab.view.*

class ProfileFragment : Fragment(), ProfileContract.View {
    private val profilePresenter: ProfilePresenter = ProfilePresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        profilePresenter.getProfileInfo()
        setProfileCarat()
        return view
    }

    override fun setProfileInfo(profile: UserData) {
        val info = profile_info_include
        Glide.with(this).load(profile.cover_image_url).into(info.profile_background_imageView)
        Glide.with(this).load(profile.profile_image_url).into(info.profile_user_imageView)
        info.profile_userName_textView.text = profile.name
        info.profile_userEmail_textView.text = profile.user_email
        info.profile_introduce_textView.text = profile.about_me
        info.profile_subscriptionDay_textView.text = profile.created_at
        info.profile_followingCount_textView.text = profile.following_count.toString()
        info.profile_followersCount_textView.text = profile.follower_count.toString()

        info.profile_button_imageView.setOnClickListener {
            startActivity(Intent(activity, ChangeProfile::class.java))
        }
    }

    private fun setProfileCarat() {
        val tab = profile_tab_include.tabLayout_carat
        tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val pos = tab!!.position
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        profilePresenter.job.cancel()
    }
}