package com.example.carat.Ui.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carat.Model.DetailTimeLineData
import com.example.carat.Model.UserData
import com.example.carat.Presenter.Profile.ProfileContract
import com.example.carat.Presenter.Profile.ProfilePresenter
import com.example.carat.R
import com.example.carat.Ui.Activity.CaringActivity
import com.example.carat.Ui.Activity.ChangeProfile
import com.example.carat.Ui.Activity.DetailCaratActivity
import com.example.carat.Ui.Activity.FollowActivity
import com.example.carat.Ui.Adapter.ProfileTimeLineAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.layout_profile_info.view.*
import kotlinx.android.synthetic.main.layout_profile_tab.view.*

class ProfileFragment : Fragment(), ProfileContract.View {
    private val profilePresenter: ProfilePresenter = ProfilePresenter(this)
    private val MOVE_REQUEST_CODE = 102
    private var profileTimeLineData: ArrayList<DetailTimeLineData> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        profilePresenter.getProfileInfo()
        setProfileTimeLine()
        return view
    }

    private fun setProfileTimeLine() {
        val tab = profile_tab_include.tabLayout_carat
        tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab!!.position == 0) {
                    if (profileTimeLineData.isNotEmpty()) {
                        profilePresenter.getCaring(profileTimeLineData.last().post_time)
                        profile_tab_include.tabLayout_show_recyclerView.addOnScrollListener(object :
                            RecyclerView.OnScrollListener() {
                            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                                super.onScrolled(recyclerView, dx, dy)
                                val lastVisibleItemPosition =
                                    (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                                val itemTotalCount = recyclerView.adapter!!.itemCount - 1
                                if (lastVisibleItemPosition == itemTotalCount) {
                                    profilePresenter.getCaring(profileTimeLineData.last().post_time)
                                }
                            }
                        })
                    } else {
                        profilePresenter.getCarat("")
                    }
                } else {
                    if (profileTimeLineData.isNotEmpty()) {
                        profilePresenter.getCarat(profileTimeLineData.last().post_time)
                        profile_tab_include.tabLayout_show_recyclerView.addOnScrollListener(object :
                            RecyclerView.OnScrollListener() {
                            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                                super.onScrolled(recyclerView, dx, dy)
                                val lastVisibleItemPosition =
                                    (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                                val itemTotalCount = recyclerView.adapter!!.itemCount - 1
                                if (lastVisibleItemPosition == itemTotalCount) {
                                    profilePresenter.getCarat(profileTimeLineData.last().post_time)
                                }
                            }
                        })
                    } else {
                        profilePresenter.getCarat("")
                    }
                }
            }
        })
    }

    override fun setProfileInfo(profile: UserData) {
        if (profile.message != "") {
            profile_info_include.apply {
                Glide.with(this).load(profile.cover_image_url).into(profile_background_imageView)
                Glide.with(this).load(profile.profile_image_url).into(profile_user_imageView)
                profile_userName_textView.text = profile.name
                profile_userEmail_textView.text = profile.user_email
                profile_introduce_textView.text = profile.about_me
                profile_subscriptionDay_textView.text = profile.created_at
                profile_followingCount_textView.text = profile.following_count.toString()
                profile_followersCount_textView.text = profile.follower_count.toString()

                val intent = Intent(activity, FollowActivity::class.java)
                profile_followingCount_textView.setOnClickListener { startActivity(intent) }
                profile_followersCount_textView.setOnClickListener { startActivity(intent) }
                profile_button_imageView.setOnClickListener {
                    val editProfileIntent = Intent(activity, ChangeProfile::class.java)
                    startActivityForResult(editProfileIntent, MOVE_REQUEST_CODE)
                }
            }
        } else {
            Toast.makeText(activity, profile.message, Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK && requestCode == MOVE_REQUEST_CODE) {
            activity?.startActivity(data)
            activity?.finish()
        }
    }

    override fun setProfileAdapter(result: ArrayList<DetailTimeLineData>, name: String) {
        val toDetail = { data: DetailTimeLineData ->
            val intent = Intent(context, DetailCaratActivity::class.java)
            if (data.recaring_id != "") {
                intent.putExtra("id", data.recaring_id)
            } else {
                intent.putExtra("id", data.caring_id)
            }

            requireActivity().startActivity(intent)
        }

        val toCaring = { data: DetailTimeLineData ->
            val intent = Intent(context, CaringActivity::class.java)
            if (data.recaring_id != "") {
                intent.putExtra("id", data.recaring_id)
            } else {
                intent.putExtra("id", data.caring_id)
            }
            intent.putExtra("isCaring", data.am_i_recaring)
            requireActivity().startActivity(intent)
        }

        val toCarat = { data: DetailTimeLineData ->
            val intent = Intent(context, CaringActivity::class.java)
            if (data.recaring_id != "") {
                intent.putExtra("id", data.recaring_id)
            } else {
                intent.putExtra("id", data.caring_id)
            }
            intent.putExtra("isCarat", data.am_i_carat)
            requireActivity().startActivity(intent)
        }

        profileTimeLineData = result
        profile_tab_include.tabLayout_show_recyclerView.adapter =
            ProfileTimeLineAdapter(result, name, toDetail, toCaring, toCarat)
    }

    override fun onDestroy() {
        super.onDestroy()
        profilePresenter.job.cancel()
    }
}