package com.example.carat.Util

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.example.carat.R
import kotlinx.android.synthetic.main.layout_dialog.*
import java.util.*

class CustomDialog(
    context: Context?,
    private val mLeftClickListener: View.OnClickListener?,
    private val mRightClickListener: View.OnClickListener?
) : Dialog(context!!, android.R.style.Theme_Translucent_NoTitleBar) {

    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)

        blurringTheBackground()
        settingTheClickEvents()
    }

    private fun blurringTheBackground() {
        val lpWindow = WindowManager.LayoutParams()
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
        lpWindow.dimAmount = 0.8f
        Objects.requireNonNull(window)?.attributes = lpWindow
        setContentView(R.layout.layout_dialog)
    }

    private fun settingTheClickEvents() {
        if (mLeftClickListener != null && mRightClickListener != null) {
            mLeftButton.setOnClickListener(mLeftClickListener)
            mRightButton.setOnClickListener(mRightClickListener)
        }
    }
}