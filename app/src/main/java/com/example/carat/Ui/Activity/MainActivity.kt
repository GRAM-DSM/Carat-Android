package com.example.carat.Ui.Activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.carat.Model.UserObject
import com.example.carat.R
import com.example.carat.Repository.Repository
import com.example.carat.Ui.Fragment.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var prevMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clickWritingButton()
        setBottomNavigation()
    }

    private fun clickWritingButton() {
        main_writing_floatingActionButton.setOnClickListener {
            val intent = Intent(this, WritingActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setBottomNavigation() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.menu_home_item, Fragment())
            .commitAllowingStateLoss()

        main_tab_bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home_item -> {
                    transaction.replace(R.id.main_frameLayout, ProfileFragment())
                        .commitAllowingStateLoss()

                    it.setIcon(R.drawable.icon_home_fill)
                    main_tab_bottomNavigation.menu.findItem(R.id.menu_profile_item)
                        .setIcon(R.drawable.icon_profile_outline)
                }
                R.id.menu_profile_item -> {
                    if (intent.getBooleanExtra("follow", false)) {
                        main_tab_bottomNavigation.selectedItemId = R.id.menu_profile_item
                        UserObject.getInstance().email = intent.getStringExtra("email") ?: UserObject.getInstance().email
                    } else {
                        UserObject.getInstance().email = Repository().getEmail()!!
                    }

                    transaction.replace(R.id.main_frameLayout, ProfileFragment())
                        .commitAllowingStateLoss()

                    it.setIcon(R.drawable.icon_profile_fill)
                    main_tab_bottomNavigation.menu.findItem(R.id.menu_home_item)
                        .setIcon(R.drawable.icon_home_outline)
                }
            }

            true
        }
    }

//    private fun setupViewPager() {
//        main_page_viewpager.adapter = ViewPagerAdapter(supportFragmentManager)
//    }
//
//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.menu_home_item -> main_page_viewpager.currentItem = 0
//            R.id.menu_profile_item -> main_page_viewpager.currentItem = 1
//        }
//        return false
//    }
//
//    override fun onPageSelected(position: Int) {
//        if (prevMenuItem != null) {
//            prevMenuItem!!.isChecked = false
//        } else {
//            main_tab_bottomNavigation.menu.getItem(0).isChecked = false
//        }
//
//        main_tab_bottomNavigation.menu.getItem(position).isChecked = true
//        prevMenuItem = main_tab_bottomNavigation.menu.getItem(position)
//    }
//
//    override fun onPageScrollStateChanged(state: Int) {}
//    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
}