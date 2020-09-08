package com.example.carat.Ui.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.carat.Model.TokenData
import com.example.carat.Model.UserObject
import com.example.carat.R
import com.example.carat.Repository.Repository
import com.example.carat.Ui.Adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getToken()
        clickWritingButton()
        setPagerAdapter()
        setBottomNavigation()
    }

    private fun getToken() {
        val token = TokenData.getInstance()
        if(token.access_token.isEmpty() || token.refresh_token.isEmpty()) {
            TokenData.getInstance().access_token = Repository().getAccess() ?: ""
            TokenData.getInstance().refresh_token = Repository().getRefresh() ?: ""
        }

        Log.e("main", Repository().getAccess().toString())
    }

    private fun clickWritingButton() {
        main_writing_floatingActionButton.setOnClickListener {
            val intent = Intent(this, WritingActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setPagerAdapter() {
        main_frameLayout.adapter = ViewPagerAdapter(supportFragmentManager)
        main_frameLayout.currentItem = 0
    }

    private fun setBottomNavigation() {
        main_tab_bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home_item -> {
                    it.setIcon(R.drawable.icon_home_fill)
                    main_tab_bottomNavigation.menu.findItem(R.id.menu_profile_item)
                        .setIcon(R.drawable.icon_profile_outline)
                    main_frameLayout.currentItem = 0
                }
                R.id.menu_profile_item -> {
                    it.setIcon(R.drawable.icon_profile_fill)
                    main_tab_bottomNavigation.menu.findItem(R.id.menu_home_item)
                        .setIcon(R.drawable.icon_home_outline)
                    main_frameLayout.currentItem = 1
                }
            }

            true
        }
    }
}