package com.example.carat.Ui.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carat.Model.DetailTimeLineData
import com.example.carat.R
import kotlinx.android.synthetic.main.item_carat_post.view.*

class TimeLineAdapter(private val timeLinePostList: ArrayList<DetailTimeLineData>) :
    RecyclerView.Adapter<TimeLineAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setImages(itemImages: ArrayList<String>) {
            itemView.apply {
                if (itemImages.size == 1) {
                    Glide.with(context).load(itemImages[0]).into(itemCarat_grid1_imageView)
                }
                if (itemImages.size == 2) {
                    Glide.with(context).load(itemImages[1]).into(itemCarat_grid2_imageView)
                }
                if (itemImages.size == 3) {
                    Glide.with(context).load(itemImages[2]).into(itemCarat_grid3_imageView)
                }
                if (itemImages.size == 4) {
                    Glide.with(context).load(itemImages[3]).into(itemCarat_grid4_imageView)
                }
            }
        }

        fun setCaring(me_recaring: Boolean) {
            itemView.apply {
                if (me_recaring) {
                    itemCarat_reCaring_imageView.setImageResource(R.drawable.icon_re)
                    itemCarat_reCaring_textView.setTextColor(
                        ContextCompat.getColor(context, R.color.mainColor)
                    )
                } else {
                    itemCarat_reCaring_imageView.setImageResource(R.drawable.icon_re_gray)
                    itemCarat_reCaring_textView.setTextColor(
                        ContextCompat.getColor(context, R.color.gray)
                    )
                }
            }
        }

        fun setCarats(me_carat: Boolean) {
            itemView.apply {
                if (me_carat) {
                    itemCarat_like_imageView.setImageResource(R.drawable.icon_maiin_logo)
                    itemCarat_like_textView.setTextColor(
                        ContextCompat.getColor(context, R.color.mainColor)
                    )
                } else {
                    itemCarat_like_imageView.setImageResource(R.drawable.icon_carat_gray)
                    itemCarat_like_textView.setTextColor(
                        ContextCompat.getColor(context, R.color.gray)
                    )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_carat_post, parent, false)
    )

    override fun getItemCount(): Int = timeLinePostList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val view = holder.itemView
        val item: DetailTimeLineData = timeLinePostList[position]

        view.apply {
            Glide.with(context).load(item.owner.profile_image).circleCrop()
                .into(itemCarat_profile_imageView)
            itemCarat_name_textView.text = item.owner.id
            itemCarat_email_textView.text = item.owner.email
            itemCarat_time_textView.text = item.post_time
            itemCarat_content_textView.text = item.body
            itemCarat_reCaring_textView.text = item.recaring_count.toString()
            itemCarat_like_textView.text = item.carat_count.toString()
            holder.setImages(item.body_images)
            holder.setCarats(item.am_i_recaring)
            holder.setCaring(item.am_i_carat)
        }
    }
}