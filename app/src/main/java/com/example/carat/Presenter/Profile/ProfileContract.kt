package com.example.carat.Presenter.Profile

import com.example.carat.Model.Post
import com.example.carat.Model.UserData

interface ProfileContract {
    interface View {
        fun setProfileInfo(profile: UserData)
        fun setProfileAdapter(post: ArrayList<Post>)
    }

    interface Presenter {
        fun getProfileInfo()
        fun getCarat()
        fun getCaring()
    }
}