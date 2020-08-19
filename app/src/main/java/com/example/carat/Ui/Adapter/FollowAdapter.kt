package com.example.carat.Ui.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.carat.Model.FollowData
import com.example.carat.R

class FollowAdapter(val list: ArrayList<FollowData>) :
    RecyclerView.Adapter<FollowAdapter.FollowVH>() {

    class FollowVH(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowVH =
        FollowVH(LayoutInflater.from(parent.context).inflate(R.layout.item_follow, parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: FollowVH, position: Int) {
        val data: FollowData = list[position]
    }
}