package com.example.carat.Presenter.SignInUp

import com.example.carat.Model.LoginData
import com.example.carat.Model.SignData
import com.example.carat.Repository.Repository
import com.example.carat.Util.BaseCoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.await

class CreateUserPresenter(var view: CreateUserContract.View) :
    CreateUserContract.Presenter, BaseCoroutineScope() {

    private val repository: Repository = Repository()

    override fun sendDataToServer(signData: SignData) {
        launch(handler) {
            repository.doSignUp(signData).await()
            withContext(Dispatchers.Main) {
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