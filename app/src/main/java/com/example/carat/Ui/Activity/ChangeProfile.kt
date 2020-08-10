package com.example.carat.Ui.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.carat.Model.UserData
import com.example.carat.Presenter.Profile.ChangeProfilePresenter
import com.example.carat.R
import com.example.carat.Ui.Fragment.LoginFragment
import com.example.carat.Ui.Util.CustomDialog
import com.example.carat.Ui.Util.SetActionBar
import kotlinx.android.synthetic.main.activity_change_profile.*
import kotlinx.android.synthetic.main.widget_appbar.view.*

class ChangeProfile : AppCompatActivity() {
    private val changeProfilePresenter = ChangeProfilePresenter()
    private var customDialog: CustomDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_profile)

        settingActionBar()
        initUserInfo()
        setDialog()
    }

    private fun settingActionBar() {
        SetActionBar(this, changeProfile_appbar_include.widget_toolbar).apply {
            setSupportActionBar(appbar)

            setBackKey(false, View.OnClickListener { finish() })
            setTitle("프로필 수정")
            setSave("저장하기", View.OnClickListener {
                changeProfilePresenter.uploadCarat()
                finish()
            })
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

    private fun setDialog() {
        val negativeListener = View.OnClickListener {
            customDialog?.dismiss()
        }

        val positiveListener = View.OnClickListener {
            changeProfilePresenter.doLogOut()
            customDialog?.dismiss()

            val intent = Intent(this, LoginFragment::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()
        }

        customDialog = CustomDialog(this, negativeListener, positiveListener)
        customDialog!!.show()
    }
}