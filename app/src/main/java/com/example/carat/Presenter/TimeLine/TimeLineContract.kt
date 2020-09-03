package com.example.carat.Presenter.TimeLine

import com.example.carat.Model.TimeLineData

interface TimeLineContract {

    interface View {

    }

    interface Presenter{
        suspend fun getPost(postData: TimeLineData)
    }
}