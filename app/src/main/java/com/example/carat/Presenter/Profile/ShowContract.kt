package com.example.carat.Presenter.Profile

import com.example.carat.Model.DetailTimeLineData
import com.example.carat.Model.UserData
import com.example.carat.Ui.Adapter.ProfileTimeLineAdapter

interface ShowContract {
    interface View {
        fun setProfileInfo(profile: UserData)
        fun setProfileAdapter(result: ArrayList<DetailTimeLineData>, name: String)
    }

    interface Presenter {
        fun getProfileInfo()
        fun doFollow()
        fun cancelFollow()
        fun getCarat(time: String)
        fun getCaring(time: String)
    }
}