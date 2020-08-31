package com.example.carat.Ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carat.Model.TimeLinePost
import com.example.carat.R
import kotlinx.android.synthetic.main.item_carat_post.view.*

class TimeLineAdapter(val context: Context, val timeLinePostList: ArrayList<TimeLinePost>) :
    RecyclerView.Adapter<TimeLineAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_carat_post, parent, false)
    )

    override fun getItemCount(): Int = timeLinePostList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val view = holder.itemView
        val item: TimeLinePost = timeLinePostList[position]

        view.apply {
            Glide.with(context).load(item.owner.profile_image).circleCrop()
                .into(itemCarat_profile_imageView)
            itemCarat_name_textView.text = item.owner.id
            itemCarat_email_textView.text = item.owner.email
            itemCarat_time_textView.text = item.post_time
            itemCarat_content_textView.text = item.body
            itemCarat_reCaring_textView.text = item.recaring_count.toString()
            itemCarat_like_textView.text = item.carat_count.toString()
            setImages(this, item)
            setCarats(this, item)
        }
    }

    private fun setImages(view: View, item: TimeLinePost) {
        val image = item.body_images

        view.apply {
            if (image.size == 1) {
                Glide.with(context).load(item.body_images[0]).into(itemCarat_grid1_imageView)
            }
            if (image.size == 2) {
                Glide.with(context).load(item.body_images[1]).into(itemCarat_grid2_imageView)
            }
            if (image.size == 3) {
                Glide.with(context).load(item.body_images[2]).into(itemCarat_grid3_imageView)
            }
            if (image.size == 4) {
                Glide.with(context).load(item.body_images[3]).into(itemCarat_grid4_imageView)
            }
        }
    }

    private fun setCarats(view: View, item: TimeLinePost) {
        if (item.me_recaring) {
            view.itemCarat_reCaring_imageView.setImageResource(R.drawable.icon_re)
            view.itemCarat_reCaring_textView.setTextColor(
                ContextCompat.getColor(context, R.color.mainColor)
            )
        } else {
            view.itemCarat_reCaring_imageView.setImageResource(R.drawable.icon_re_gray)
            view.itemCarat_reCaring_textView.setTextColor(
                ContextCompat.getColor(context, R.color.gray)
            )
        }

        if (item.me_carat) {
            view.itemCarat_like_imageView.setImageResource(R.drawable.icon_maiin_logo)
            view.itemCarat_like_textView.setTextColor(
                ContextCompat.getColor(context, R.color.mainColor)
            )
        } else {
            view.itemCarat_like_imageView.setImageResource(R.drawable.icon_carat_gray)
            view.itemCarat_like_textView.setTextColor(
                ContextCompat.getColor(context, R.color.gray)
            )
        }
    }
}