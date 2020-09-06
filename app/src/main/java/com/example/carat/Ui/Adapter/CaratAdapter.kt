package com.example.carat.Ui.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carat.Model.CaratUnitData
import com.example.carat.R
import kotlinx.android.synthetic.main.item_follow.view.*

class CaratAdapter(
    private val caringList: ArrayList<CaratUnitData>,
    private val sendFollowData: (CaratUnitData) -> Unit,
    private val showUser: (CaratUnitData) -> Unit
) : RecyclerView.Adapter<CaratAdapter.CaringVH>() {

    class CaringVH(view: View) : RecyclerView.ViewHolder(view) {
        fun distinguishButtonImage(data: CaratUnitData, btn: Button) {
            if (data.is_follower) {
                btn.background = getDrawable(itemView.context, R.drawable.following_button)
            } else {
                btn.background = getDrawable(itemView.context, R.drawable.to_follow_button)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CaringVH =
        CaringVH(LayoutInflater.from(parent.context).inflate(R.layout.item_follow, parent, false))

    override fun getItemCount(): Int = caringList.size

    override fun onBindViewHolder(holder: CaringVH, position: Int) {
        val data: CaratUnitData = caringList[position]
        holder.itemView.apply {
            Glide.with(this)
                .load(data.image)
                .circleCrop()
                .into(itemFollow_profile_imageView)
            itemFollow_profile_imageView.setOnClickListener { showUser(data) }

            itemFollow_name_textView.text = data.id
            itemFollow_email_textView.text = data.email

            holder.distinguishButtonImage(data, itemFollow_follow_button)
            itemFollow_follow_button.setOnClickListener {
                data.is_follower = !data.is_follower
                holder.distinguishButtonImage(data, itemFollow_follow_button)
                sendFollowData(data)
            }
        }
    }
}