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
            view.setFollowAdapter(
                repository.getFollowingList(UserObject.getInstance().email).followings
            )
        }
    }

    override fun getFollowerList() {
        CoroutineScope(coroutineContext).launch(handler) {
            view.setFollowAdapter(
                repository.getFollowersList(UserObject.getInstance().email).followers
            )
        }
    }

    override fun sendFollowingState() {}
    override fun sendFollowerState() {}
}