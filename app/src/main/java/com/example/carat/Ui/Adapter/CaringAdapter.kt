package com.example.carat.Ui.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carat.Model.FollowData
import com.example.carat.R
import kotlinx.android.synthetic.main.item_follow.view.*

class CaringAdapter(
    private val caringList: ArrayList<FollowData>,
    private val sendFollowData: (FollowData) -> Unit,
    private val showUser: (FollowData) -> Unit
) : RecyclerView.Adapter<CaringAdapter.CaringVH>() {

    class CaringVH(view: View) : RecyclerView.ViewHolder(view) {
        fun distinguishButtonImage(data: FollowData, btn: Button) {
            if (data.following) {
                btn.background =
                    AppCompatResources.getDrawable(itemView.context, R.drawable.following_button)
            } else {
                btn.background =
                    AppCompatResources.getDrawable(itemView.context, R.drawable.to_follow_button)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CaringVH =
        CaringVH(LayoutInflater.from(parent.context).inflate(R.layout.item_follow, parent, false))

    override fun getItemCount(): Int = caringList.size

    override fun onBindViewHolder(holder: CaringVH, position: Int) {
        val data: FollowData = caringList[position]
        holder.itemView.apply {
            Glide.with(this)
                .load(data.profile_image)
                .circleCrop()
                .into(itemFollow_profile_imageView)
            itemFollow_profile_imageView.setOnClickListener { showUser(data) }

            itemFollow_name_textView.text = data.name
            itemFollow_email_textView.text = data.email

            holder.distinguishButtonImage(data, itemFollow_follow_button)
            itemFollow_follow_button.setOnClickListener {
                data.following = !data.following
                holder.distinguishButtonImage(data, itemFollow_follow_button)
                sendFollowData(data)
            }
        }
    }
}