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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_follow)

        settingActionBar()
        setTabBar()
    }

    private fun settingActionBar() {
        SetActionBar(this, follow_appbar_include.widget_toolbar).apply {
            setBackKey(false) { finish() }
            this.setTitle(UserObject.getInstance().name ?: "")
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
                val pos = tab!!.position
                if (pos == 0) {
                    followPresenter.getFollowingList()
                } else {
                    followPresenter.getFollowerList()
                }
            }
        })
    }

    override fun setFollowAdapter(followData: ArrayList<FollowData>) {
        val toFollow = { data: FollowData, view: View ->
            val btn = view.itemFollow_follow_button
            distinguishButtonImage(data, btn)

            btn.setOnClickListener {
                data.following = !data.following
                distinguishButtonImage(data, btn)

                if (data.currentIndex == 0) {
                    followPresenter.sendFollowingState(data.email)
                } else {
                    followPresenter.sendFollowerState(data.email)
                }
            }
        }

        val showUser = { it: FollowData ->
            UserObject.getInstance().email = it.email
            val intent = Intent(this, ShowUserActivity::class.java)
            intent.putExtra("follow", true)
            intent.putExtra("isFollow", it.following)
            intent.putExtra("email", it.email)
            startActivity(intent)
            finish()
        }

        tabLayout_show_recyclerView.adapter = FollowAdapter(followData, toFollow, showUser)
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun distinguishButtonImage(data: FollowData, btn: Button) {
        if (data.following) {
            btn.background = getDrawable(R.drawable.following_button)
        } else {
            btn.background = getDrawable(R.drawable.to_follow_button)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        followPresenter.job.cancel()
    }
}