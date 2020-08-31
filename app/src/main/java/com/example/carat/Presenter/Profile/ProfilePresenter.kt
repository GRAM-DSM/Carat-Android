package com.example.carat.Presenter.Profile

import com.example.carat.Model.RequestCaringData
import com.example.carat.Model.UserObject
import com.example.carat.Repository.Repository
import com.example.carat.Ui.Adapter.ProfileTimeLineAdapter
import com.example.carat.Util.BaseCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ProfilePresenter(var view: ProfileContract.View) : ProfileContract.Presenter,
    BaseCoroutineScope() {

    private val repository: Repository = Repository()

    override fun getProfileInfo() {
        CoroutineScope(coroutineContext).launch(handler) {
            val result = repository.getProfile(UserObject.getInstance().email)
            view.setProfileInfo(result)
        }
    }

    override fun getCaring(caringId: String) {
        val parameter = RequestCaringData(last_caring_id = caringId.toInt())

        CoroutineScope(coroutineContext).launch(handler) {
            repository.getCaringTimeLine(UserObject.getInstance().email, parameter).apply {
                if (message == "") {
                    view.setProfileAdapter(
                        ProfileTimeLineAdapter(result, UserObject.getInstance().name)
                    )
                }
            }
        }
    }

    override fun getCarat(caringId: String) {
        val parameter = RequestCaringData(last_caring_id = caringId.toInt())

        CoroutineScope(coroutineContext).launch(handler) {
            repository.getCaratTimeLine(UserObject.getInstance().email, parameter).apply {
                if (message == "") {
                    view.setProfileAdapter(ProfileTimeLineAdapter(result))
                }
            }

        }
    }
}