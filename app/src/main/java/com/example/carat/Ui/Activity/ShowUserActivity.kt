package com.example.carat.Ui.Activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.carat.Model.UserData
import com.example.carat.Presenter.Profile.ShowContract
import com.example.carat.Presenter.Profile.ShowPresenter
import com.example.carat.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_show_user.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.layout_profile_info.view.*
import kotlinx.android.synthetic.main.layout_profile_tab.view.*

class ShowUserActivity : AppCompatActivity(), ShowContract.View {
    private val showPresenter: ShowPresenter = ShowPresenter(this)
    val email: String by lazy {
        intent.getStringExtra("email")!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_user)

        if (intent.getBooleanExtra("follow", false)) {
            showPresenter.getProfileInfo(email)
            setProfileCarat()
        } else {
            finish()
        }
    }

    override fun setProfileInfo(profile: UserData) {
        if (profile.message != "") {
            val info = show_info_include
            Glide.with(this).load(profile.cover_image_url).into(info.profile_background_imageView)
            Glide.with(this).load(profile.profile_image_url).into(info.profile_user_imageView)
            info.profile_userName_textView.text = profile.name
            info.profile_userEmail_textView.text = profile.user_email
            info.profile_introduce_textView.text = profile.about_me
            info.profile_subscriptionDay_textView.text = profile.created_at
            info.profile_followingCount_textView.text = profile.following_count.toString()
            info.profile_followersCount_textView.text = profile.follower_count.toString()
            setButtonAction()
        } else {
            Toast.makeText(this, profile.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun setButtonAction() {
        val isFollow = intent.getBooleanExtra("isFollow", false)

        if (isFollow) {
            show_info_include.profile_button_imageView.setImageResource(R.drawable.following_button)
            show_info_include.profile_button_imageView.setOnClickListener {
                showPresenter.cancelFollow(email)
            }
        } else {
            show_info_include.profile_button_imageView.setImageResource(R.drawable.to_follow_button)
            show_info_include.profile_button_imageView.setOnClickListener {
                showPresenter.doFollow(email)
            }
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
        showPresenter.job.cancel()
    }
}