package com.example.carat.Presenter.SignInUp

import android.util.Log
import com.example.carat.Model.LoginData
import com.example.carat.Model.SignData
import com.example.carat.Repository.Repository
import com.example.carat.Util.BaseCoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreateUserPresenter(var view: CreateUserContract.View) :
    CreateUserContract.Presenter, BaseCoroutineScope() {

    private val repository: Repository = Repository()

    override fun sendDataToServer(signData: SignData) {
        launch(handler) {
            val result = withContext(Dispatchers.IO) {
                val response = repository.doSignUp(signData)
                Log.e("CreateUserPresenter", response.code().toString())
                Log.e("CreateUserPresenter", response.isSuccessful.toString())
                Log.e("CreateUserPresenter", response.message())
                response.body()
            }

            if (result == null) {
                saveEmail(signData.email)
                setLoginData(signData)
                view.moveToLogin()
            } else {
                view.showMessage(result.message)
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