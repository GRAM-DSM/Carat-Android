package com.example.carat.Ui.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.example.carat.Presenter.WritingContract
import com.example.carat.Presenter.WritingPresenter
import com.example.carat.R
import kotlinx.android.synthetic.main.activity_writing.*
import kotlinx.android.synthetic.main.widget_appbar.view.*

class WritingActivity : AppCompatActivity(), WritingContract.View {
    private val writingPresenter: WritingContract.Presenter by lazy {
        WritingPresenter(this)
    }
    private val appbar: Toolbar by lazy {
        writing_appbar_include.widget_toolbar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_writing)

        settingActionBar()
        getProfileImage()
        setClickEvents()
    }

    private fun settingActionBar() {
        appbar.apply {
            setSupportActionBar(this)
            appbar_backKey_imageView.setImageResource(R.drawable.icon_close_button)
            appbar_title_textView.visibility = View.GONE
            appbar_save_textView.text = "캐링하기"
        }
    }

    private fun getProfileImage() {
        writingPresenter.getProfileImage()
    }

    private fun setClickEvents() {
        appbar.apply {
            appbar_backKey_imageView.setOnClickListener {
                finish()
            }
            appbar_save_textView.setOnClickListener {
                writingPresenter.saveContent()
                finish()
            }
        }
    }

    override fun setProfileImageForm(result: String) {
        Glide.with(this)
            .load(result)
            .error(R.drawable.image_default_profile)
            .circleCrop()
            .into(writing_profile_imageView)
    }
}