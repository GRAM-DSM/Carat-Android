package com.example.carat.Ui.Util

import android.content.Context
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.example.carat.R
import kotlinx.android.synthetic.main.widget_appbar.view.*

class SetActionBar(var context: Context, var appbar: Toolbar) {
    fun setTitle(title: String, isVisible: Boolean = true) {
        appbar.appbar_title_textView.text = title
        appbar.appbar_title_textView.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    fun setBackKey() {
        appbar.appbar_backKey_imageView.setImageResource(R.drawable.icon_close_button)
    }

    fun setSave(text: String, isColor: Boolean = false) {
        appbar.appbar_save_textView.text = text
        if (isColor) {
            appbar.appbar_save_textView.setTextColor(context.resources.getColor(R.color.mainColor))
        } else {
            appbar.appbar_save_textView.setTextColor(context.resources.getColor(R.color.gray))
        }
    }
}