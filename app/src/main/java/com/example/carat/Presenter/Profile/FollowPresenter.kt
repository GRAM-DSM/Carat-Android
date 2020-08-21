package com.example.carat.Presenter.Profile

import com.example.carat.Model.UserObject
import com.example.carat.Repository.Repository
import com.example.carat.Util.BaseCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class FollowPresenter(val view: FollowContract.View) : FollowContract.Presenter,
    BaseCoroutineScope() {

    private val repository: Repository = Repository()

    override fun getFollowingList() {
        CoroutineScope(coroutineContext).launch(handler) {
            val result = repository.getFollowingList(UserObject.getInstance().email)
            if (result.message != "") {
                view.setFollowAdapter(result.followings)
            } else {
                view.showError(result.message)
            }
        }
    }

    override fun getFollowerList() {
        CoroutineScope(coroutineContext).launch(handler) {
            val result = repository.getFollowersList(UserObject.getInstance().email)
            if (result.message != "") {
                view.setFollowAdapter(result.followers)
            } else {
                view.showError(result.message)
            }
        }
    }

    override fun sendFollowingState(email: String) {
        CoroutineScope(coroutineContext).launch(handler) {
            repository.doFollow(UserObject.getInstance().email)
        }
    }

    override fun sendFollowerState(email: String) {
        CoroutineScope(coroutineContext).launch(handler) {
            repository.cancelFollow(UserObject.getInstance().email)
        }
    }
}