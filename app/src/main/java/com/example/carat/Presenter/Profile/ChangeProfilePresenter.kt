package com.example.carat.Presenter.Profile

import com.example.carat.Model.EditUserData
import com.example.carat.Model.UserObject
import com.example.carat.Repository.Repository
import com.example.carat.Util.BaseCoroutineScope

class ChangeProfilePresenter(val view: ChangeProfileContract.View) :
    ChangeProfileContract.Presenter, BaseCoroutineScope() {

    val data = UserObject.getInstance()
    private val repository: Repository = Repository()

    override fun doLogOut() {

    }

    override fun updateProfile(editUserData: EditUserData) {
        if (editUserData.name != data.name || editUserData.name != data.name) {
            if (editUserData.profileUri != null || editUserData.backUri != null) {
                view.convertToImage(editUserData)
            } else {
                repository
            }
        }
    }

    override fun updateProfileWithImage(editUserData: EditUserData) {
        repository
    }
}