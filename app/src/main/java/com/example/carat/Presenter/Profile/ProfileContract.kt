package com.example.carat.Presenter.Profile

import com.example.carat.Model.UserData

interface ProfileContract {
    interface View {
        fun setProfileInfo(profile: UserData)
    }

    interface Presenter {
        fun getProfileInfo()
        fun getCarat()
        fun getCaring()
    }
}