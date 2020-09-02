package com.example.carat.Presenter.Profile

import com.example.carat.Model.EditUserData
import com.example.carat.Model.TokenData
import com.example.carat.Model.UserObject
import com.example.carat.Repository.Repository
import com.example.carat.Util.BaseCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class ChangeProfilePresenter(val view: ChangeProfileContract.View) :
    ChangeProfileContract.Presenter, BaseCoroutineScope() {

    private val data = UserObject.getInstance()
    private val repository: Repository = Repository()
    private val hashMap: HashMap<String, MultipartBody.Part> = HashMap()
    private var name: String = ""
    private var about: String = ""

    override fun doLogOut() {
        TokenData.getInstance().access_token = ""
        TokenData.getInstance().refresh_token = ""
        repository.saveToken("", "")
        view.moveLoginPage()
    }

    override fun updateProfile(editUserData: EditUserData) {
        if (data.name != editUserData.name || data.introduction != editUserData.intro) {
            hashMap.clear()
            name = editUserData.name
            about = editUserData.intro

            if (editUserData.profileUri != null || editUserData.backUri != null) {
                view.convertToImage(editUserData)
            } else {
                CoroutineScope(coroutineContext).launch(handler) {
                    repository.updateProfile(hashMap, name, about)
                }
            }
        }
    }

    override fun updateProfileWithImage(editUserData: EditUserData) {
        if (editUserData.profileToUpload != null) {
            hashMap["profile_image"] = editUserData.profileToUpload!!
        }
        if (editUserData.backToUpload != null) {
            hashMap["cover_image"] = editUserData.backToUpload!!
        }

        CoroutineScope(coroutineContext).launch(handler) {
            repository.updateProfile(hashMap, name, about)
        }
    }
}