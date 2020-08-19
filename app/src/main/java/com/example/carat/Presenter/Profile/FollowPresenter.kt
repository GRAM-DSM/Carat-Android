package com.example.carat.Presenter.Profile

import com.example.carat.Util.BaseCoroutineScope

class FollowPresenter(var view: FollowContract.View) : FollowContract.Presenter,
    BaseCoroutineScope() {
    override fun getFollowingList() {}
    override fun getFollowerList() {}
    override fun sendFollowingState() {}
    override fun sendFollowerState() {}
}