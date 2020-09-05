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

class ProfileTimeLineAdapter(
    val profilePostList: ArrayList<DetailTimeLineData>,
    val name: String = ""
) : RecyclerView.Adapter<ProfileTimeLineAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_carat_post, parent, false)
    )

    override fun getItemCount(): Int = profilePostList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val view = holder.itemView
        val item: DetailTimeLineData = profilePostList[position]

        view.apply {
            Glide.with(context).load(item.owner.profile_image).circleCrop()
                .into(itemCarat_profile_imageView)
            itemCarat_name_textView.text = item.owner.id
            itemCarat_email_textView.text = item.owner.email
            itemCarat_time_textView.text = item.post_time
            itemCarat_content_textView.text = item.body
            itemCarat_reCaring_textView.text = item.recaring_count.toString()
            itemCarat_like_textView.text = item.carat_count.toString()

            holder.setReCaring(name, item)
            holder.setImages(item)
            holder.setCarats(item)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setReCaring(name: String, item: DetailTimeLineData) {
            if (name != "") {
                itemView.apply {
                    if (item.am_i_recaring) {
                        itemCarat_reCaring_textView.text = "$name 님이 리캐링한 캐링"
                        itemCarat_reCaring_imageView.visibility = View.VISIBLE
                        itemCarat_reCaring_textView.visibility = View.VISIBLE
                    } else {
                        itemCarat_reCaring_imageView.visibility = View.GONE
                        itemCarat_reCaring_textView.visibility = View.GONE
                    }
                }
            }
        }

        fun setImages(item: DetailTimeLineData) {
            val image = item.body_images

            itemView.apply {
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

        fun setCarats(item: DetailTimeLineData) {
            itemView.apply {
                if (item.am_i_recaring) {
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

                if (item.am_i_carat) {
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
}