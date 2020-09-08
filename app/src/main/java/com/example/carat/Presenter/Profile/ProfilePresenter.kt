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

class ProfilePresenter(var view: ProfileContract.View) : ProfileContract.Presenter,
    BaseCoroutineScope() {

    private val repository: Repository = Repository()

    override fun getProfileInfo() {
        launch(handler) {
            val result = withContext(Dispatchers.IO) {
                val response = repository.getProfile(UserObject.getInstance().email)
                Log.e("ProfilePresenter", response.code().toString())
                Log.e("ProfilePresenter", response.isSuccessful.toString())
                Log.e("ProfilePresenter", response.message())
                response.body()
            }
            if (result != null) {
                view.setProfileInfo(result)
            }
        }
    }

    override fun getCaring(time: String) {
        val parameter = RequestCaringData(base_time = time)

        launch(handler) {
            repository.getCaringTimeLine(UserObject.getInstance().email, parameter).apply {
                if (message == "") {
                    view.setProfileAdapter(result, UserObject.getInstance().name)
                }
            }
        }
    }

    override fun getCarat(time: String) {
        val parameter = RequestCaringData(base_time = time)

        launch(handler) {
            repository.getCaratTimeLine(UserObject.getInstance().email, parameter).apply {
                if (message == "") {
                    view.setProfileAdapter(result, "")
                }
            }

        }
    }
}