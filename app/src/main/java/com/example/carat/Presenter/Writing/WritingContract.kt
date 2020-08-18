package com.example.carat.Presenter.Writing

interface WritingContract {
    interface View {
        fun setProfileImageForm(result: String)
    }

    interface Presenter {
        fun saveContent()
        fun getProfileImage()
    }
}