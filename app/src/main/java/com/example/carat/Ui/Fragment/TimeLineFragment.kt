package com.example.carat.Ui.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carat.Model.DetailTimeLineData
import com.example.carat.Model.RequestCaringData
import com.example.carat.Presenter.TimeLine.TimeLineContract
import com.example.carat.Presenter.TimeLine.TimeLinePresenter
import com.example.carat.R
import com.example.carat.Ui.Activity.CaringActivity
import com.example.carat.Ui.Activity.DetailCaratActivity
import com.example.carat.Ui.Adapter.TimeLineAdapter
import kotlinx.android.synthetic.main.fragment_time_line.*
import kotlinx.android.synthetic.main.fragment_time_line.view.*

class TimeLineFragment : Fragment(), TimeLineContract.View {
    private var timeLineData: ArrayList<DetailTimeLineData> = arrayListOf()
    private val timeLinePresenter = TimeLinePresenter(this)
    private lateinit var timeLineView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        timeLineView = inflater.inflate(R.layout.fragment_time_line, container, false)
        setUserInformation()
        refreshRecyclerViewItem()
        return view
    }

    private fun setUserInformation() {
        timeLinePresenter.getUserData()
    }

    private fun refreshRecyclerViewItem() {
        timeLineView.timeline_recyclerView.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter!!.itemCount - 1
                if (lastVisibleItemPosition == itemTotalCount) {
                    timeLinePresenter.getPost(RequestCaringData(base_time = timeLineData.last().post_time))
                }
            }
        })
    }

    override fun getTimeLine() {
        timeLinePresenter.getPost(RequestCaringData())
    }

    override fun setTimeLineAdapter(timeLineArray: ArrayList<DetailTimeLineData>) {
        val toDetail = { data: DetailTimeLineData ->
            val intent = Intent(context, DetailCaratActivity::class.java)
            if (data.recaring_id != "") {
                intent.putExtra("id", data.recaring_id)
            } else {
                intent.putExtra("id", data.caring_id)
            }

            requireActivity().startActivity(intent)
        }

        val toCaring = { data: DetailTimeLineData ->
            val intent = Intent(context, CaringActivity::class.java)
            if (data.recaring_id != "") {
                intent.putExtra("id", data.recaring_id)
            } else {
                intent.putExtra("id", data.caring_id)
            }
            intent.putExtra("isCaring", data.am_i_recaring)
            requireActivity().startActivity(intent)
        }

        val toCarat = { data: DetailTimeLineData ->
            val intent = Intent(context, CaringActivity::class.java)
            if (data.recaring_id != "") {
                intent.putExtra("id", data.recaring_id)
            } else {
                intent.putExtra("id", data.caring_id)
            }
            intent.putExtra("isCarat", data.am_i_carat)
            requireActivity().startActivity(intent)
        }

        timeline_recyclerView.adapter = TimeLineAdapter(timeLineArray, toDetail, toCaring, toCarat)
    }

    override fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}