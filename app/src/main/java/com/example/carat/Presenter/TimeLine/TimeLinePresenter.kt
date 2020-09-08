package com.example.carat.Presenter.TimeLine

import android.util.Log
import com.example.carat.Model.RequestCaringData
import com.example.carat.Model.TimeLineData
import com.example.carat.Model.TokenData
import com.example.carat.Model.UserObject
import com.example.carat.Repository.Repository
import com.example.carat.Util.BaseCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TimeLinePresenter(private var view: TimeLineContract.View) : TimeLineContract.Presenter,
    BaseCoroutineScope() {

    private val repository: Repository = Repository()

    override fun getPost(requestCaringData: RequestCaringData) {
        launch(handler) {
            val result = withContext(Dispatchers.IO) {
                val response = repository.getTimeLine(requestCaringData)
                Log.e("TimeLinePresenter", response.code().toString())
                Log.e("TimeLinePresenter", response.isSuccessful.toString())
                Log.e("TimeLinePresenter", response.message())
                response.body()
            }
            if (result?.message == null) {
                view.setTimeLineAdapter(result!!.result)
            } else {
                view.showMessage(result.message)
            }
        }
    }

    override fun getUserData() {
        launch(handler) {
            val email: String = repository.getEmail() ?: ""
            val result = withContext(Dispatchers.IO) {
                val response = repository.getProfile(email)
                Log.e("TimeLinePresenter", response.code().toString())
                Log.e("TimeLinePresenter", response.isSuccessful.toString())
                Log.e("TimeLinePresenter", response.message())
                response.body()
            }

            if (result?.message == null) {
                UserObject.getInstance().apply {
                    background = result!!.cover_image_url
                    profile = result.profile_image_url
                    name = result.name
                    this.email = result.user_email
                    introduction = result.about_me
                    date = result.created_at
                    following = result.following_count.toString()
                    follower = result.follower_count.toString()
                }

                view.getTimeLine()
            } else {
                view.showMessage(result.message)
            }
        }
    }
}