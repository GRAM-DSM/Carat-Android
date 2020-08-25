package com.example.carat.Presenter.SignInUp

import com.example.carat.Repository.Repository
import com.example.carat.Util.BaseCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class LoginPresenter(var view: LoginContract.View) : LoginContract.Presenter,
    BaseCoroutineScope() {

    override fun sendDataToServer(email: String, password: String) {
        CoroutineScope(coroutineContext).launch {

        }
    }
}