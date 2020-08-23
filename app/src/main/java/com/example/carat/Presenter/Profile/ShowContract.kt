package com.example.carat.Presenter.Profile

import com.example.carat.Model.UserData

interface ShowContract {
    interface View {
        fun setProfileInfo(profile: UserData)
    }

    interface Presenter {
        fun getProfileInfo(email: String)
        fun doFollow(email: String)
        fun cancelFollow(email: String)
        fun getCarat()
        fun getCaring()
    }
}