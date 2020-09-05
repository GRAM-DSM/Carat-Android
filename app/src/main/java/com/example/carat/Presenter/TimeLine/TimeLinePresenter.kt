package com.example.carat.Presenter.TimeLine

import com.example.carat.Model.RequestCaringData
import com.example.carat.Model.TimeLineData
import com.example.carat.Model.UserObject
import com.example.carat.Repository.Repository
import com.example.carat.Util.BaseCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class TimeLinePresenter(private var view: TimeLineContract.View) : TimeLineContract.Presenter,
    BaseCoroutineScope() {

    private val requestCaringData: RequestCaringData = RequestCaringData()
    private val repository: Repository = Repository()

    override fun getPost(postData: TimeLineData) {
        CoroutineScope(coroutineContext).launch {
            val result = repository.getTimeLine(requestCaringData)
            if (result.message != "") {
                view.showMessage(result.message)
            } else {
                view.setTimeLineAdapter(result.result)
            }
        }
    }

    override fun getUserData() {
        CoroutineScope(coroutineContext).launch {
            val email: String = repository.getEmail()!!
            val result = repository.getProfile(email)
            UserObject.getInstance().apply {
                background = result.cover_image_url
                profile = result.profile_image_url
                name = result.name
                this.email = result.user_email
                introduction = result.about_me
                date = result.created_at
                following = result.following_count.toString()
                follower = result.follower_count.toString()
            }
        }
    }
}