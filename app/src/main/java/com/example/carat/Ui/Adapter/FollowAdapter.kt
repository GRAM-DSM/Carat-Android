package com.example.carat.Ui.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carat.Model.FollowData
import com.example.carat.R
import kotlinx.android.synthetic.main.item_follow.view.*

class FollowAdapter(
    private val followList: ArrayList<FollowData>,
    private val settingButtonView: (FollowData, View) -> Unit,
    private val showUser: (FollowData) -> Unit
) : RecyclerView.Adapter<FollowAdapter.FollowVH>() {
    class FollowVH(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowVH =
        FollowVH(LayoutInflater.from(parent.context).inflate(R.layout.item_follow, parent, false))

    override fun getItemCount(): Int = followList.size

    override fun onBindViewHolder(holder: FollowVH, position: Int) {
        val data: FollowData = followList[position]
        holder.itemView.apply {
            Glide.with(this)
                .load(data.profile_image)
                .circleCrop()
                .into(itemFollow_profile_imageView)
            itemFollow_profile_imageView.setOnClickListener { showUser(data) }
            itemFollow_name_textView.text = data.name
            itemFollow_email_textView.text = data.email
            settingButtonView(data, this)
        }
    }
}