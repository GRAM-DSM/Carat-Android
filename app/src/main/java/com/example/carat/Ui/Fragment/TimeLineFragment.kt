package com.example.carat.Ui.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.carat.Model.DetailTimeLineData
import com.example.carat.Presenter.TimeLine.TimeLineContract
import com.example.carat.Presenter.TimeLine.TimeLinePresenter
import com.example.carat.R
import com.example.carat.Ui.Adapter.TimeLineAdapter
import kotlinx.android.synthetic.main.fragment_time_line.*

class TimeLineFragment : Fragment(), TimeLineContract.View {
    val timeLinePresenter = TimeLinePresenter(this)
    var detailCaratArray: ArrayList<DetailTimeLineData> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setUserInformation()
        return inflater.inflate(R.layout.fragment_time_line, container, false)
    }

    private fun setUserInformation() {
        timeLinePresenter.getUserData()
    }

    override fun setTimeLineAdapter(timeLineAdapter: TimeLineAdapter) {
        timeline_recyclerView.adapter = timeLineAdapter
    }

    override fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}