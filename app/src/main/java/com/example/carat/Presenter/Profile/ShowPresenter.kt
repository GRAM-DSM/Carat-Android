package com.example.carat.Presenter.Profile

import com.example.carat.Repository.Repository
import com.example.carat.Util.BaseCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ShowPresenter(val view: ShowContract.View) : ShowContract.Presenter, BaseCoroutineScope() {
    private val repository: Repository = Repository()

    override fun getProfileInfo(email: String) {
        CoroutineScope(coroutineContext).launch(handler) {
            view.setProfileInfo(repository.getProfile(email))
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


    override fun getCarat() {

    }

    override fun getCaring() {

    }
}