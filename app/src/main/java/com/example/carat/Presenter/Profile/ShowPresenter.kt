package com.example.carat.Presenter.Profile

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


    override fun getCaring() {
        val hashMap = HashMap<String, Int>()
        hashMap["size"] = 0
        hashMap["last_caring_id"] = 0

        CoroutineScope(coroutineContext).launch(handler) {
            repository.getCaringTimeLine(email, hashMap).apply {
                if (message == "") {
                    view.setProfileAdapter(ProfileTimeLineAdapter(result, userName))
                }
            }
        }
    }

    override fun getCarat() {
        val hashMap = HashMap<String, Int>()
        hashMap["size"] = 0
        hashMap["last_caring_id"] = 0

        CoroutineScope(coroutineContext).launch(handler) {
            repository.getCaratTimeLine(email, hashMap).apply {
                if (message == "") {
                    view.setProfileAdapter(ProfileTimeLineAdapter(result))
                }
            }
        }
    }
}