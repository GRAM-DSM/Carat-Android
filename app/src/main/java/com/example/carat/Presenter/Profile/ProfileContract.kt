package com.example.carat.Presenter.Profile

import com.example.carat.Model.UserData
import com.example.carat.Ui.Adapter.ProfileTimeLineAdapter

interface ProfileContract {
    interface View {
        fun setProfileInfo(profile: UserData)
        fun setProfileAdapter(profileAdapter: ProfileTimeLineAdapter)
    }

    interface Presenter {
        fun getProfileInfo()
        fun getCarat(caringId: String)
        fun getCaring(caringId: String)
    }
}