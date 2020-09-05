package com.example.carat.Ui.Activity

import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.carat.Model.DetailTimeLineData
import com.example.carat.Presenter.TimeLine.DetailCaratContract
import com.example.carat.Presenter.TimeLine.DetailCaratPresenter
import com.example.carat.R
import com.example.carat.Util.SetActionBar
import kotlinx.android.synthetic.main.activity_detail_carat.*
import kotlinx.android.synthetic.main.widget_appbar.view.*
import java.util.*

class DetailCaratActivity : AppCompatActivity(), DetailCaratContract.View {
    private val presenter: DetailCaratContract.Presenter = DetailCaratPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_carat)

        if (intent.hasExtra("id")) {
            presenter.getDetailCarat(intent.getStringExtra("id")!!)
            settingActionBar()
        } else {
            finish()
        }
    }

    private fun settingActionBar() {
        SetActionBar(this, detail_appbar_include.widget_toolbar).apply {
            setSupportActionBar(appbar)
            setBackKey(false) { finish() }
            setTitle("캐링")
        }
    }

    override fun setDetailCarat(result: DetailTimeLineData) {
        setTopInfo(result)
        setContent(result)
        setBottomInfo(result)
    }

    private fun setTopInfo(result: DetailTimeLineData) {
        Glide.with(this).load(result.owner.profile_image).circleCrop()
            .into(detailCarat_profile_imageView)
        detailCarat_name_textView.text = result.owner.id
        detailCarat_email_textView.text = result.owner.email
    }

    private fun setContent(result: DetailTimeLineData) {
        detail_content_textView.text = result.body
        if (result.body_images.isNotEmpty()) {
            setImages(result.body_images)
        }
    }

    private fun setImages(bodyImages: ArrayList<String>) {
        val layoutParams = ViewGroup.LayoutParams(277, 243)

        for (i in bodyImages) {
            ImageView(this).apply {
                this.layoutParams = layoutParams
                setPadding(0, 24, 0, 0)
                Glide.with(this@DetailCaratActivity).load(i).into(this)
                linearLayout_contentImages.addView(this)
            }
        }
    }

    private fun setBottomInfo(result: DetailTimeLineData) {
        detail_time_textView.text = result.post_time
        detailCarat_like_textView.text = result.carat_count.toString()
        detailCarat_reCaring_textView.text = result.recaring_count.toString()

    }
}