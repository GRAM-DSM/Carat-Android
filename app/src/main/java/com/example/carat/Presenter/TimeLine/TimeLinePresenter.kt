package com.example.carat.Presenter.TimeLine

import android.widget.Toast
import com.example.carat.Model.DetailTimeLineData
import com.example.carat.Model.RequestCaringData
import com.example.carat.Model.TimeLineData
import com.example.carat.Repository.Repository
import com.example.carat.Ui.Adapter.TimeLineAdapter
import com.example.carat.Util.BaseCoroutineScope
import com.example.carat.Util.MyApp.Companion.context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class TimeLinePresenter(var view: TimeLineContract.View): TimeLineContract.Presenter,
    BaseCoroutineScope() {

    private val requestCaringData: RequestCaringData = RequestCaringData()
    private val repository: Repository= Repository()

    override suspend fun getPost(postData: TimeLineData) {
        CoroutineScope(coroutineContext).launch {
            val result = repository.getTimeLine(requestCaringData)
            when(result.message){
                "a bad request" -> {
                    Toast.makeText(context, "잘못된 요청", Toast.LENGTH_SHORT).show()
                }
                "Your request has been forbidden" -> {
                    Toast.makeText(context, "접근 권한이 없습니다", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    view.setTimeLineAdapter(TimeLineAdapter(result.result))
                }
            }
        }
    }

}