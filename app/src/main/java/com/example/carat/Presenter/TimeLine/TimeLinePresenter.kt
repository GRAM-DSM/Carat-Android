package com.example.carat.Presenter.TimeLine

import com.example.carat.Model.RequestCaringData
import com.example.carat.Model.TimeLineData
import com.example.carat.Repository.Repository
import com.example.carat.Util.BaseCoroutineScope

class TimeLinePresenter: TimeLineContract.Presenter,
    BaseCoroutineScope() {

    private val requestCaringData: RequestCaringData = RequestCaringData()

    private val repository: Repository= Repository()

    override suspend fun getPost(postData: TimeLineData) {
        repository.getTimeLine(requestCaringData)
    }

}