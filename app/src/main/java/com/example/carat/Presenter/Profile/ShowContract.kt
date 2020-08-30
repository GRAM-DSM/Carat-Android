package com.example.carat.Presenter.Profile

import com.example.carat.Model.ProfileTimeLinePost
import com.example.carat.Model.UserData

interface ShowContract {
    interface View {
        fun setProfileInfo(profile: UserData)
        fun setProfileAdapter(profilePost: ArrayList<ProfileTimeLinePost>, name: String)
    }

    interface Presenter {
        fun getProfileInfo(email: String)
        fun doFollow(email: String)
        fun cancelFollow(email: String)
        fun getCarat()
        fun getCaring()
    }
}