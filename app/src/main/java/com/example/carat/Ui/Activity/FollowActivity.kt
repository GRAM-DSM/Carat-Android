package com.example.carat.Ui.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.carat.Model.FollowData
import com.example.carat.Model.UserObject
import com.example.carat.Presenter.Profile.FollowContract
import com.example.carat.Presenter.Profile.FollowPresenter
import com.example.carat.R
import com.example.carat.Ui.Adapter.FollowAdapter
import com.example.carat.Util.SetActionBar
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_follow.*
import kotlinx.android.synthetic.main.item_follow.view.*
import kotlinx.android.synthetic.main.layout_profile_tab.*
import kotlinx.android.synthetic.main.layout_profile_tab.view.*
import kotlinx.android.synthetic.main.widget_appbar.view.*

class FollowActivity : AppCompatActivity(), FollowContract.View {
    private val followPresenter: FollowPresenter = FollowPresenter(this)
    private var indexOnTab: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_follow)

        settingActionBar()
        setTabBar()
    }

    private fun settingActionBar() {
        SetActionBar(this, follow_appbar_include.widget_toolbar).apply {
            setBackKey(false) { finish() }
            setTitle(UserObject.getInstance().name ?: "")
        }
    }

    private fun setTabBar() {
        val tab = follow_tab_include.tabLayout_carat
        tab.getTabAt(0)?.text = "팔로잉"
        tab.getTabAt(1)?.text = "팔로워"
        tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabSelected(tab: TabLayout.Tab?) {
                indexOnTab = tab!!.position
                val email = intent.getStringExtra("email") ?: UserObject.getInstance().email
                if (indexOnTab == 0) {
                    followPresenter.getFollowingList(email)
                } else {
                    followPresenter.getFollowerList(email)
                }
            }
        })
    }

    override fun setFollowAdapter(followData: ArrayList<FollowData>) {
        follow_tab_include.tabLayout_carat
        val toFollow = { data: FollowData ->
            if (indexOnTab == 0) {
                followPresenter.sendFollowingState(data.email)
            } else {
                followPresenter.sendFollowerState(data.email)
            }
        }

        val showUser = { data: FollowData ->
            val intent = Intent(this, ShowUserActivity::class.java)
            intent.putExtra("isFollow", data.following)
            intent.putExtra("email", data.email)
            startActivity(intent)
            finish()
        }

        tabLayout_show_recyclerView.adapter = FollowAdapter(followData, toFollow, showUser)
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        followPresenter.job.cancel()
    }
}