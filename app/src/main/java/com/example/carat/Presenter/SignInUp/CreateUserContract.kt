package com.example.carat.Presenter.SignInUp

interface CreateUserContract {

    interface View {
        fun getData()
    }

    interface Presenter{
        fun saveData()
        fun sendDataToServer()
    }
}