package com.example.carat.Presenter.Profile

interface ProfileContract {
    interface View {
        fun setProfileInfo()
    }

    interface Presenter {
        fun getProfileInfo()
        fun getCarat()
        fun getCaring()
    }
}