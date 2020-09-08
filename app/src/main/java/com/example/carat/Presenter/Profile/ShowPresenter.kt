package com.example.carat.Presenter.Profile

import android.util.Log
import com.example.carat.Model.RequestCaringData
import com.example.carat.Model.UserObject
import com.example.carat.Repository.Repository
import com.example.carat.Ui.Adapter.ProfileTimeLineAdapter
import com.example.carat.Util.BaseCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.await

class ShowPresenter(val view: ShowContract.View, val email: String) : ShowContract.Presenter,
    BaseCoroutineScope() {

    private val repository: Repository = Repository()
    var userName: String = ""

    override fun getProfileInfo() {
        launch(handler) {
            val result = withContext(Dispatchers.IO) {
                val response = repository.getProfile(email)
                Log.e("ShowPresenter", response.code().toString())
                Log.e("ShowPresenter", response.isSuccessful.toString())
                Log.e("ShowPresenter", response.message())
                response.body()
            }

            if (result != null) {
                userName = result.name
                view.setProfileInfo(result)
            }
        }
    }

    override fun doFollow() {
        launch(handler) {
            repository.doFollow(email).await()
        }
    }

    override fun cancelFollow() {
        launch(handler) {
            repository.cancelFollow(email).await()
        }
    }


    override fun getCaring(time: String) {
        val parameter = RequestCaringData(base_time = time)

        launch(handler) {
            repository.getCaringTimeLine(email, parameter).apply {
                if (message == "") {
                    view.setProfileAdapter(result, userName)
                }
            }
        }
    }

    override fun getCarat(time: String) {
        val parameter = RequestCaringData(base_time = time)

        launch(handler) {
            repository.getCaratTimeLine(email, parameter).apply {
                if (message == "") {
                    view.setProfileAdapter(result, "")
                }
            }
        }
    }
}