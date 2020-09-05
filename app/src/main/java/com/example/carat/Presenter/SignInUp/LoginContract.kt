package com.example.carat.Presenter.SignInUp

interface LoginContract {
    interface View {
        fun moveToMain()
        fun showMessage(message: String)
    }

    interface Presenter {
        fun sendDataToServer()
    }
}