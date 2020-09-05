package com.example.carat.Ui.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carat.Model.DetailTimeLineData
import com.example.carat.R


class TimeLineFragment : Fragment() {

    var detailCaratArray: ArrayList<DetailTimeLineData> = ArrayList()
    lateinit var mRecyclerView : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var rootView = inflater.inflate(R.layout.fragment_time_line,container,false)

        mRecyclerView = rootView.findViewById(R.id.timeline_recyclerView)
        mRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

        mRecyclerView.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {

            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                return false
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) { }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) { }

        })
        return inflater.inflate(R.layout.fragment_time_line, container, false)
    }

}