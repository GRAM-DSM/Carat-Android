package com.example.carat.Presenter.SignInUp

import com.example.carat.Model.SignData

interface CreateUserContract {

    interface View {
        fun showMessage(message: String)
        fun moveToLogin()
    }

    interface Presenter{
        fun sendDataToServer(signData: SignData)
    }
}