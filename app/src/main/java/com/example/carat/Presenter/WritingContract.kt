package com.example.carat.Presenter

interface WritingContract {
    interface View {
        fun setProfileImageForm(result: String)
    }

    interface Presenter{
        fun saveContent()
        fun getProfileImage()
    }
}