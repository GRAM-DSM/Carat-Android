package com.example.carat.Ui.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.carat.Model.UserObject
import com.example.carat.Presenter.Profile.ChangeProfilePresenter
import com.example.carat.R
import com.example.carat.Ui.Fragment.LoginFragment
import com.example.carat.Util.CustomDialog
import com.example.carat.Util.SetActionBar
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

            setBackKey(false) { finish() }
            setTitle("프로필 수정")
            setSave("저장하기") {
                changeProfilePresenter.updateProfile()
                finish()
            }
        }
    }

    private fun initUserInfo() {
        val data = UserObject.getInstance()

        Glide.with(this)
            .load(data.background)
            .error(R.drawable.image_default_background)
            .into(changeProfile_cover_imageView)
        Glide.with(this)
            .load(data.profile)
            .error(R.drawable.image_default_profile)
            .circleCrop()
            .into(changeProfile_profile_imageView)
        changeProfile_name_editText.setText(data.name)
        changeProfile_introduction_editText.setText(data.introduction)
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

    override fun onDestroy() {
        super.onDestroy()
        changeProfilePresenter.job.cancel()
    }
}