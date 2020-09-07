package com.example.carat.Presenter.Profile

import com.example.carat.Repository.Repository
import com.example.carat.Util.BaseCoroutineScope
import kotlinx.coroutines.launch
import retrofit2.await

class FollowPresenter(val view: FollowContract.View) : FollowContract.Presenter,
    BaseCoroutineScope() {

    private val repository: Repository = Repository()

    override fun getFollowingList(email: String) {
        launch(handler) {
            val result = repository.getFollowingList(email)
            if (result.message != "") {
                view.setFollowAdapter(result.followings)
            } else {
                view.showError(result.message)
            }
        }
    }

    override fun getFollowerList(email: String) {
        launch(handler) {
            val result = repository.getFollowersList(email)
            if (result.message != "") {
                view.setFollowAdapter(result.followers)
            } else {
                view.showError(result.message)
            }
        }
    }

    override fun sendFollowingState(email: String) {
        launch(handler) {
            repository.doFollow(email).await()
        }
    }

    override fun sendFollowerState(email: String) {
        launch(handler) {
            repository.cancelFollow(email).await()
        }
    }
}