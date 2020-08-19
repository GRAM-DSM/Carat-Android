package com.example.carat.Presenter.Profile

interface FollowContract {
    interface View {
        fun changeButtonImage()
    }

    interface Presenter {
        fun getFollowingList()
        fun getFollowerList()
        fun sendFollowingState()
        fun sendFollowerState()
    }
}