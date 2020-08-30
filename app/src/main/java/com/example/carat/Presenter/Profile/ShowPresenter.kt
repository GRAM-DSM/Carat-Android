package com.example.carat.Presenter.Profile

import com.example.carat.Model.UserObject
import com.example.carat.Repository.Repository
import com.example.carat.Util.BaseCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ShowPresenter(val view: ShowContract.View) : ShowContract.Presenter, BaseCoroutineScope() {
    private val repository: Repository = Repository()

    var userName: String = ""

    override fun getProfileInfo(email: String) {
        CoroutineScope(coroutineContext).launch(handler) {
            val result = repository.getProfile(email)
            userName = result.name
            view.setProfileInfo(result)
        }
    }

    override fun doFollow(email: String) {
        CoroutineScope(coroutineContext).launch(handler) {
            repository.doFollow(email)
        }
    }

    override fun cancelFollow(email: String) {
        CoroutineScope(coroutineContext).launch(handler) {
            repository.cancelFollow(email)
        }
    }


    override fun getCaring() {
        val hashMap = HashMap<String, Int>()
        hashMap["size"] = 0
        hashMap["last_caring_id"] = 0

        CoroutineScope(coroutineContext).launch(handler) {
            repository.getCaringTimeLine(UserObject.getInstance().email, hashMap).apply {
                if (message == "") {
                    view.setProfileAdapter(result, userName)
                }
            }
        }
    }

    override fun getCarat() {
        val hashMap = HashMap<String, Int>()
        hashMap["size"] = 0
        hashMap["last_caring_id"] = 0

        CoroutineScope(coroutineContext).launch(handler) {
            repository.getCaratTimeLine(UserObject.getInstance().email, hashMap).apply {
                if (message == "") {
                    view.setProfileAdapter(result, "")
                }
            }

        }
    }
}