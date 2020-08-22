package com.example.carat.Presenter.Profile

import com.example.carat.Util.BaseCoroutineScope

class ProfilePresenter(var view: ProfileContract.View) : ProfileContract.Presenter,
    BaseCoroutineScope() {
    override fun getProfileInfo() {

    }
}