package com.example.carat.Presenter.SignInUp

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
            repository.doLogin()
            withContext(Dispatchers.Main) {
                saveData()
                view.moveToMain()
            }
        }
    }

    private fun saveData() {
        repository.saveToken(
            TokenData.getInstance().access_token,
            TokenData.getInstance().refresh_token
        )
        repository.saveLoginState(true)
    }
}