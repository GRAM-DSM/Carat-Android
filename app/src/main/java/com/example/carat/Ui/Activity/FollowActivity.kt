package com.example.carat.Ui.Activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.carat.Model.UserData
import com.example.carat.Presenter.Profile.FollowContract
import com.example.carat.R
import com.example.carat.Util.SetActionBar
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_follow.*
import kotlinx.android.synthetic.main.layout_profile_tab.view.*
import kotlinx.android.synthetic.main.widget_appbar.view.*

class FollowActivity : AppCompatActivity(), FollowContract.View {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_follow)

        settingActionBar()
        setTabBar()
    }

    private fun settingActionBar() {
        SetActionBar(this, follow_appbar_include.widget_toolbar).apply {
            setBackKey(false, View.OnClickListener { finish() })
            this.setTitle(UserData.name ?: "")
        }
    }

    private fun setTabBar() {
        val tab = follow_tab_include.tabLayout_carat
        tab.getTabAt(0)?.text = "팔로잉"
        tab.getTabAt(1)?.text = "팔로워"
        tab.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val pos = tab!!.position
//                follow_tab_include.tabLayout_show_recyclerView.adapter = if (pos == 0) {
//
//                } else if(pos == 1) {
//
//                }
            }
        })
    }
}