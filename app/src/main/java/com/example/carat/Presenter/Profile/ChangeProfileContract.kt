package com.example.carat.Presenter.Profile

interface ChangeProfileContract {
    interface Presenter {
        fun doLogOut()
        fun updateProfile()
        fun selectImage()
    }
}