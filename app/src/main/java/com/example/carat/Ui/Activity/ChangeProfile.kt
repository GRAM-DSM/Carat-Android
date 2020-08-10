package com.example.carat.Ui.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.example.carat.Model.UserData
import com.example.carat.Presenter.Profile.ChangeProfileContract
import com.example.carat.R
import kotlinx.android.synthetic.main.activity_change_profile.*
import kotlinx.android.synthetic.main.widget_appbar.view.*

class ChangeProfile : AppCompatActivity(), ChangeProfileContract.View {
    private val appbar: Toolbar by lazy {
        changeProfile_appbar_include.widget_toolbar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_profile)

        settingActionBar()
        initUserInfo()
    }

    private fun settingActionBar() {
        appbar.apply {
            setSupportActionBar(this)
            appbar_title_textView.text = "프로필 수정"
            appbar_save_textView.text = "저장하기"
        }
    }

    private fun initUserInfo() {
        Glide.with(this)
            .load(UserData.background)
            .error(R.drawable.image_default_background)
            .into(changeProfile_cover_imageView)
        Glide.with(this)
            .load(UserData.profile)
            .error(R.drawable.image_default_profile)
            .circleCrop()
            .into(changeProfile_profile_imageView)
        changeProfile_name_editText.setText(UserData.name)
        changeProfile_introduction_editText.setText(UserData.introduction)
    }
}