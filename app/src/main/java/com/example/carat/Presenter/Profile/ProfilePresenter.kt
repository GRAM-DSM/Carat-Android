package com.example.carat.Presenter.Profile

import com.example.carat.Model.ProfileTimeLineParameter
import com.example.carat.Model.UserObject
import com.example.carat.Repository.Repository
import com.example.carat.Util.BaseCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ProfilePresenter(var view: ProfileContract.View) : ProfileContract.Presenter,
    BaseCoroutineScope() {

    private val repository: Repository = Repository()

    override fun getProfileInfo() {
        CoroutineScope(coroutineContext).launch(handler) {
            view.setProfileInfo(repository.getProfile(UserObject.getInstance().email))
        }
    }

    override fun getCarat() {
        CoroutineScope(coroutineContext).launch(handler) {
            val result = repository.getProfileTimeLine(
                UserObject.getInstance().email,
                ProfileTimeLineParameter()
            )

            if (result.message == "") {
                view.setProfileAdapter(result.result)
            }

        }
    }

    override fun getCaring() {
        CoroutineScope(coroutineContext).launch(handler) {
            view.setProfileInfo(repository.getProfile(UserObject.getInstance().email))
        }
    }
}