package com.example.carat.Presenter.TimeLine

import com.example.carat.Model.DetailTimeLineData
import com.example.carat.Model.RequestCaringData
import com.example.carat.Model.TimeLineData

interface TimeLineContract {
    interface View {
        fun getTimeLine()
        fun setTimeLineAdapter(timeLineArray: ArrayList<DetailTimeLineData>)
        fun showMessage(message: String)
    }

    interface Presenter{
        fun getPost(requestCaringData: RequestCaringData)
        fun getUserData()
    }
}