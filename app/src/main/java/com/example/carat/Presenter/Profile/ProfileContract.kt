package com.example.carat.Presenter.Profile

import com.example.carat.Model.ProfileTimeLinePost
import com.example.carat.Model.UserData

interface ProfileContract {
    interface View {
        fun setProfileInfo(profile: UserData)
        fun setProfileAdapter(profilePost: ArrayList<ProfileTimeLinePost>, name: String)
    }

    interface Presenter {
        fun getProfileInfo()
        fun getCarat()
        fun getCaring()
    }
}