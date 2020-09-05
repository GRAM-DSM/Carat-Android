package com.example.carat.Presenter.Profile

import com.example.carat.Model.RequestCaringData
import com.example.carat.Repository.Repository
import com.example.carat.Ui.Adapter.ProfileTimeLineAdapter
import com.example.carat.Util.BaseCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ShowPresenter(val view: ShowContract.View, val email: String) : ShowContract.Presenter,
    BaseCoroutineScope() {

    private val repository: Repository = Repository()
    var userName: String = ""


    override fun getProfileInfo() {
        CoroutineScope(coroutineContext).launch(handler) {
            val result = repository.getProfile(email)
            userName = result.name
            view.setProfileInfo(result)
        }
    }

    override fun doFollow() {
        CoroutineScope(coroutineContext).launch(handler) {
            repository.doFollow(email)
        }
    }

    override fun cancelFollow() {
        CoroutineScope(coroutineContext).launch(handler) {
            repository.cancelFollow(email)
        }
    }


    override fun getCaring(time: String) {
        val parameter = RequestCaringData(base_time = time)

        CoroutineScope(coroutineContext).launch(handler) {
            repository.getCaringTimeLine(email, parameter).apply {
                if (message == "") {
                    view.setProfileAdapter(result, userName)
                }
            }
        }
    }

    override fun getCarat(time: String) {
        val parameter = RequestCaringData(base_time = time)

        CoroutineScope(coroutineContext).launch(handler) {
            repository.getCaratTimeLine(email, parameter).apply {
                if (message == "") {
                    view.setProfileAdapter(result, "")
                }
            }
        }
    }
}