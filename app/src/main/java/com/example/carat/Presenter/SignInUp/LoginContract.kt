package com.example.carat.Presenter.SignInUp

interface LoginContract {

    interface View {
        fun getData()
    }

    interface Presenter{
        fun sendDataToServer(email:String, password:String)
    }

}