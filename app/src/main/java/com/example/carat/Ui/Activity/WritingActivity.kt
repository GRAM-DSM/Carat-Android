package com.example.carat.Ui.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.example.carat.R
import kotlinx.android.synthetic.main.activity_writing.*
import kotlinx.android.synthetic.main.widget_appbar.view.*

class WritingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_writing)

        settingActionBar()
        setProfileImageForm()
    }

    private fun settingActionBar() {
        writing_appbar_include.widget_toolbar.apply {
            setSupportActionBar(this)
            appbar_backKey_imageView.setImageResource(R.drawable.icon_close_button)
            appbar_title_textView.visibility = View.GONE
            appbar_save_textView.text = "캐링하기"
        }
    }

    private fun setProfileImageForm() {
        Glide.with(this)
            .load(R.drawable.image_default_profile)
            .circleCrop()
            .into(writing_profile_imageView)
    }
}