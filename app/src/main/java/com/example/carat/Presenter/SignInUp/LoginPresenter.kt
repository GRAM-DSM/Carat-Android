package com.example.carat.Presenter.SignInUp

import android.util.Log
import com.example.carat.Model.TokenData
import com.example.carat.Repository.Repository
import com.example.carat.Util.BaseCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginPresenter(var view: LoginContract.View) : LoginContract.Presenter,
    BaseCoroutineScope() {
    private val repository: Repository = Repository()

    override fun sendDataToServer() {
        launch(handler) {
            val result = withContext(Dispatchers.IO) {
                val response = repository.doLogin()
                Log.e("LoginPresenter", response.code().toString())
                Log.e("LoginPresenter", response.isSuccessful.toString())
                Log.e("LoginPresenter", response.message())
                response.body()
            }

            if (result?.message == null) {
                TokenData.getInstance().access_token = result!!.access_token
                TokenData.getInstance().refresh_token = result.refresh_token
                saveData()
                view.moveToMain()
            } else {
                view.showMessage(result.message)
            }
        }
    }

    private fun saveData() {
        Log.e("LoginPresenter", TokenData.getInstance().access_token)
        Log.e("LoginPresenter", TokenData.getInstance().refresh_token)

        repository.saveToken(
            TokenData.getInstance().access_token,
            TokenData.getInstance().refresh_token
        )
         repository.saveLoginState(true)
    }
}