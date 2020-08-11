package com.example.carat.Presenter.SignInUp

interface CreateUserContract {

    interface View {
        fun showInfo()
        fun getData()
    }

    interface Presenter{
        fun saveData()
        fun sendDataToServer()
        fun makeToast(msg: String)
        fun sendDataToLogin()
    }
}