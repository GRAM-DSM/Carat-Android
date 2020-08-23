package com.example.carat.Presenter.Profile

import com.example.carat.Model.FollowData

interface FollowContract {
    interface View {
        fun setFollowAdapter(followData: ArrayList<FollowData>)
        fun showError(message: String)
    }

    interface Presenter {
        fun getFollowingList()
        fun getFollowerList()
        fun sendFollowingState(email: String)
        fun sendFollowerState(email: String)
    }
}