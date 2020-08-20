package com.example.carat.Presenter.Profile

import android.net.Uri
import com.example.carat.Model.UserObject
import com.example.carat.Repository.Repository
import com.example.carat.Util.BaseCoroutineScope
import okhttp3.MultipartBody

class ChangeProfilePresenter(val view: ChangeProfileContract.View) :
    ChangeProfileContract.Presenter, BaseCoroutineScope() {

    var profileUri: Uri? = null
    var backUri: Uri? = null
    val data = UserObject.getInstance()
    private val repository: Repository = Repository()

    override fun doLogOut() {

    }

    override fun updateProfile(name: String, intro: String) {
        if (name != data.name || intro != data.name) {
            if (profileUri != null || backUri != null) {
                view.convertToImage(profileUri, backUri)
            } else {
                repository
            }
        }
    }

    override fun updateProfileWithImage(
        profileImage: MultipartBody.Part?,
        backImage: MultipartBody.Part?
    ) {
        repository
    }

    override fun setProfileImage(uri: Uri) {
        profileUri = uri
    }

    override fun setBackGroundImage(uri: Uri) {
        backUri = uri
    }
}