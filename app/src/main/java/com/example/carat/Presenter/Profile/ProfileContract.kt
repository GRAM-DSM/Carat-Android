package com.example.carat.Presenter.Profile

import com.example.carat.Model.TimeLinePost
import com.example.carat.Model.UserData

interface ProfileContract {
    interface View {
        fun setProfileInfo(profile: UserData)
        fun setProfileAdapter(timeLinePost: ArrayList<TimeLinePost>)
    }

    interface Presenter {
        fun getProfileInfo()
        fun getCarat()
        fun getCaring()
    }
}