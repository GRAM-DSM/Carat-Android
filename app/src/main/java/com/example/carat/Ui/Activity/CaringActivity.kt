package com.example.carat.Ui.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.carat.Model.CaratData
import com.example.carat.Model.CaratUnitData
import com.example.carat.Model.FollowData
import com.example.carat.Model.UserObject
import com.example.carat.Presenter.TimeLine.CaringContract
import com.example.carat.Presenter.TimeLine.CaringPresenter
import com.example.carat.R
import com.example.carat.Ui.Adapter.CaringAdapter
import com.example.carat.Ui.Adapter.FollowAdapter
import com.example.carat.Util.SetActionBar
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_caring.*
import kotlinx.android.synthetic.main.layout_profile_tab.*
import kotlinx.android.synthetic.main.layout_profile_tab.view.*
import kotlinx.android.synthetic.main.widget_appbar.view.*

class CaringActivity : AppCompatActivity(), CaringContract.View {
    private lateinit var caringPresenter: CaringPresenter
    private var indexOnTab = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_caring)

        if (intent.hasExtra("id")) {
            val id = intent.getStringExtra("id")!!
            caringPresenter = CaringPresenter(this, id)

            when {
                intent.hasExtra("isCaring") -> {
                    caringPresenter.sendCaringState(intent.getBooleanExtra("isCaring", false))
                }
                intent.hasExtra("isCarat") -> {
                    caringPresenter.sendCaratState(intent.getBooleanExtra("isCarat", false))
                }
            }

        } else {
            finish()
        }

        settingActionBar()
        setTabBar()
    }

    private fun settingActionBar() {
        SetActionBar(this, caring_appbar_include.widget_toolbar).apply {
            setBackKey(false) { finish() }
            setTitle(UserObject.getInstance().name)
        }
    }

    private fun setTabBar() {
        val tab = caring_tab_include.tabLayout_carat
        tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabSelected(tab: TabLayout.Tab?) {
                indexOnTab = tab!!.position
                if (indexOnTab == 0) {
                    caringPresenter.getCaringList()
                } else {
                    caringPresenter.getCaratList()
                }
            }
        })
    }

    override fun setCaringAdapter(caratData: ArrayList<CaratUnitData>) {
        val toFollow = { data: CaratUnitData ->
            if (indexOnTab == 0) {
                caringPresenter.sendFollowingState(data.email)
            } else {
                caringPresenter.sendFollowerState(data.email)
            }
        }

        val showUser = { data: CaratUnitData ->
            val intent = Intent(this, ShowUserActivity::class.java)
            intent.putExtra("isFollow", data.is_follower)
            intent.putExtra("email", data.email)
            startActivity(intent)
            finish()
        }

        tabLayout_show_recyclerView.adapter = CaringAdapter(caratData, toFollow, showUser)
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        caringPresenter.job.cancel()
    }
}