package com.example.carat.Ui.Util

import android.content.Context
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.example.carat.R
import kotlinx.android.synthetic.main.widget_appbar.view.*

class SetActionBar(var context: Context, var appbar: Toolbar) {
    fun setBackKey(isChange: Boolean, action: View.OnClickListener? = null) {
        if(isChange) appbar.appbar_backKey_imageView.setImageResource(R.drawable.icon_close_button)
        appbar.appbar_backKey_imageView.setOnClickListener(action)
    }

    fun setTitle(title: String, isVisible: Boolean = true) {
        appbar.appbar_title_textView.text = title
        appbar.appbar_title_textView.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    fun setSave(text: String, action: View.OnClickListener? = null) {
        appbar.appbar_save_textView.text = text
        appbar.appbar_save_textView.setOnClickListener(action)
    }
}