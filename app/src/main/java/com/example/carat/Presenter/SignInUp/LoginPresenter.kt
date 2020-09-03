package com.example.carat.Presenter.SignInUp

import com.example.carat.Repository.Repository
import com.example.carat.Util.BaseCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class LoginPresenter(var view: LoginContract.View) : LoginContract.Presenter,
    BaseCoroutineScope() {

    private val repository: Repository = Repository()
    private val hashMap: HashMap<String, String> = HashMap()

    override fun sendDataToServer(email: String, password: String) {

        if(email != null && password != null) {
            hashMap["email"] = email
            hashMap["password"] = password
        }
        CoroutineScope(coroutineContext).launch {
            repository.login(hashMap)
        }
    }
}