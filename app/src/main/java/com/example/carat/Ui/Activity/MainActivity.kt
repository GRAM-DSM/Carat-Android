package com.example.carat.Ui.Activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.carat.R
import com.example.carat.Ui.Adapter.ViewPagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener,
    ViewPager.OnPageChangeListener {
    private var prevMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clickWritingButton()
        setupViewPager()
    }

    private fun clickWritingButton() {
        main_writing_floatingActionButton.setOnClickListener {
            val intent = Intent(this, WritingActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupViewPager() {
        main_page_viewpager.adapter = ViewPagerAdapter(supportFragmentManager)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_home_item -> main_page_viewpager.currentItem = 0
            R.id.menu_profile_item -> main_page_viewpager.currentItem = 1
        }
        return false
    }

    override fun onPageSelected(position: Int) {
        if (prevMenuItem != null) {
            prevMenuItem!!.isChecked = false
        } else {
            main_tab_bottomNavigation.menu.getItem(0).isChecked = false
        }

        main_tab_bottomNavigation.menu.getItem(position).isChecked = true
        prevMenuItem = main_tab_bottomNavigation.menu.getItem(position)
    }

    override fun onPageScrollStateChanged(state: Int) {}
    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
}