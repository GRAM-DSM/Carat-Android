package com.example.carat.Ui.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.carat.Model.Post
import com.example.carat.R

class TimeLineAdapter(val postList: ArrayList<Post>) :
    RecyclerView.Adapter<TimeLineAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_carat_post,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post: Post = postList[position]

        holder.adapter_email_textView.text= post.email
        holder.adapter_name_textView.text= post.name
        holder.adapter_time_textView.text = post.time
        holder.adapter_content_textView.text = post.content
        holder.adapter_link_textView.text = post.link
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val adapter_name_textView = itemView.findViewById<TextView>(R.id.itemCarat_name_textView)
        val adapter_email_textView = itemView.findViewById<TextView>(R.id.itemCarat_email_textView)
        val adapter_time_textView = itemView.findViewById<TextView>(R.id.itemCarat_time_textView)
        val adapter_content_textView = itemView.findViewById<TextView>(R.id.itemCarat_content_textView)
        val adapter_link_textView = itemView.findViewById<TextView>(R.id.itemCarat_link_textView)
    }

}