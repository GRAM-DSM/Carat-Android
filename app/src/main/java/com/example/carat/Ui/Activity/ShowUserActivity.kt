package com.example.carat.Ui.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carat.Model.DetailTimeLineData
import com.example.carat.Model.UserData
import com.example.carat.Presenter.Profile.ShowContract
import com.example.carat.Presenter.Profile.ShowPresenter
import com.example.carat.R
import com.example.carat.Ui.Adapter.ProfileTimeLineAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_show_user.*
import kotlinx.android.synthetic.main.layout_profile_info.view.*
import kotlinx.android.synthetic.main.layout_profile_tab.view.*

class ShowUserActivity : AppCompatActivity(), ShowContract.View {
    private lateinit var showPresenter: ShowPresenter
    private var email: String = ""
    private var profileTimeLineData: ArrayList<DetailTimeLineData> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_user)

        email = intent.getStringExtra("email")!!
        showPresenter = ShowPresenter(this, email)
        showPresenter.getProfileInfo()
        setProfileCarat()
    }

    override fun setProfileInfo(profile: UserData) {
        if (profile.message != "") {
            show_info_include.apply {
                Glide.with(this).load(profile.cover_image_url).into(profile_background_imageView)
                Glide.with(this).load(profile.profile_image_url).into(profile_user_imageView)
                profile_userName_textView.text = profile.name
                profile_userEmail_textView.text = profile.user_email
                profile_introduce_textView.text = profile.about_me
                profile_subscriptionDay_textView.text = profile.created_at
                profile_followingCount_textView.text = profile.following_count.toString()
                profile_followersCount_textView.text = profile.follower_count.toString()
                profile_followingCount_textView.setOnClickListener { clickFollowText() }
                profile_followersCount_textView.setOnClickListener { clickFollowText() }
            }
            clickFollowButton(intent.getBooleanExtra("isFollow", false))
        } else {
            Toast.makeText(this, profile.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun clickFollowText() {
        val intent = Intent(this@ShowUserActivity, FollowActivity::class.java)
        intent.putExtra("email", email)
        startActivity(intent)
        finish()
    }

    private fun clickFollowButton(isFollow: Boolean) {
        if (isFollow) {
            show_info_include.profile_button_imageView.setImageResource(R.drawable.following_button)
            show_info_include.profile_button_imageView.setOnClickListener {
                showPresenter.cancelFollow()
                clickFollowButton(false)
            }
        } else {
            show_info_include.profile_button_imageView.setImageResource(R.drawable.to_follow_button)
            show_info_include.profile_button_imageView.setOnClickListener {
                showPresenter.doFollow()
            }
            clickFollowButton(true)
        }
    }

    private fun setProfileCarat() {
        val tab = show_tab_include.tabLayout_carat
        tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab!!.position == 0) {
                    if (profileTimeLineData.isNotEmpty()) {
                        showPresenter.getCaring(profileTimeLineData.last().post_time)
                        show_tab_include.tabLayout_show_recyclerView.addOnScrollListener(object :
                            RecyclerView.OnScrollListener() {
                            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                                super.onScrolled(recyclerView, dx, dy)
                                val lastVisibleItemPosition =
                                    (recyclerView.layoutManager as LinearLayoutManager)
                                        .findLastCompletelyVisibleItemPosition()
                                val itemTotalCount = recyclerView.adapter!!.itemCount - 1
                                if (lastVisibleItemPosition == itemTotalCount) {
                                    showPresenter.getCaring(profileTimeLineData.last().post_time)
                                }
                            }
                        })
                    } else {
                        showPresenter.getCaring("")
                    }
                } else {
                    if (profileTimeLineData.isNotEmpty()) {
                        showPresenter.getCarat(profileTimeLineData.last().post_time)
                        show_tab_include.tabLayout_show_recyclerView.addOnScrollListener(object :
                            RecyclerView.OnScrollListener() {
                            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                                super.onScrolled(recyclerView, dx, dy)
                                val lastVisibleItemPosition =
                                    (recyclerView.layoutManager as LinearLayoutManager)
                                        .findLastCompletelyVisibleItemPosition()
                                val itemTotalCount = recyclerView.adapter!!.itemCount - 1
                                if (lastVisibleItemPosition == itemTotalCount) {
                                    showPresenter.getCarat(profileTimeLineData.last().post_time)
                                }
                            }
                        })
                    } else {
                        showPresenter.getCarat("")
                    }
                }
            }
        })
    }

    override fun setProfileAdapter(result: ArrayList<DetailTimeLineData>, name: String) {
        val toDetail = { data: DetailTimeLineData ->
            val intent = Intent(this, DetailCaratActivity::class.java)
            if (data.recaring_id != "") {
                intent.putExtra("id", data.recaring_id)
            } else {
                intent.putExtra("id", data.caring_id)
            }
            startActivity(intent)
        }

        val toCaring = { data: DetailTimeLineData ->
            val intent = Intent(this, CaringActivity::class.java)
            if (data.recaring_id != "") {
                intent.putExtra("id", data.recaring_id)
            } else {
                intent.putExtra("id", data.caring_id)
            }
            intent.putExtra("isCaring", data.am_i_recaring)
            startActivity(intent)
        }

        val toCarat = { data: DetailTimeLineData ->
            val intent = Intent(this, CaringActivity::class.java)
            if (data.recaring_id != "") {
                intent.putExtra("id", data.recaring_id)
            } else {
                intent.putExtra("id", data.caring_id)
            }
            intent.putExtra("isCarat", data.am_i_carat)
            startActivity(intent)
        }

        profileTimeLineData = result
        show_tab_include.tabLayout_show_recyclerView.adapter =
            ProfileTimeLineAdapter(result, name, toDetail, toCaring, toCarat)
    }

    override fun onDestroy() {
        super.onDestroy()
        showPresenter.job.cancel()
    }
}