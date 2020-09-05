package com.example.carat.Presenter.SignInUp

import com.example.carat.Model.LoginData
import com.example.carat.Model.SignData
import com.example.carat.Model.UserObject
import com.example.carat.Repository.Repository
import com.example.carat.Util.BaseCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CreateUserPresenter(var view: CreateUserContract.View) :
    CreateUserContract.Presenter, BaseCoroutineScope() {

    private val repository: Repository = Repository()

    override fun sendDataToServer(signData: SignData) {
        CoroutineScope(coroutineContext).launch {
            val result = repository.doSignUp(signData)
            if (result.message != "") {
                view.showMessage(result.message)
            } else {
                saveEmail(signData.email)
                setLoginData(signData)
                view.moveToLogin()
            }
        }
    }

    private fun saveEmail(email: String) {
        repository.saveEmail(email)
    }

    private fun setLoginData(signData: SignData) {
        LoginData.getInstance().apply {
            email = signData.email
            password = signData.password
        }
    }
}