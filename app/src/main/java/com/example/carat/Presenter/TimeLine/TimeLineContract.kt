package com.example.carat.Presenter.TimeLine

import com.example.carat.Model.TimeLineData
import com.example.carat.Ui.Adapter.TimeLineAdapter

interface TimeLineContract {

    interface View {
        fun setTimeLineAdapter(timeLineAdapter: TimeLineAdapter)

    }

    interface Presenter{
        suspend fun getPost(postData: TimeLineData)
    }
}