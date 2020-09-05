package com.example.carat.Presenter.SignInUp

import com.example.carat.Model.LoginData

interface LoginContract {
    interface View {
        fun showMessage(message: String)
    }

    interface Presenter {
        fun sendDataToServer(loginData: LoginData)
    }
}