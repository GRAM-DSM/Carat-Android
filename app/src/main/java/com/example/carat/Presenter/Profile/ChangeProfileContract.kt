package com.example.carat.Presenter.Profile

import com.example.carat.Model.EditUserData

interface ChangeProfileContract {
    interface View {
        fun convertToImage(editUserData: EditUserData)
        fun moveLoginPage()
    }

    interface Presenter {
        fun doLogOut()
        fun updateProfile(editUserData: EditUserData)
        fun updateProfileWithImage(editUserData: EditUserData)
    }
}