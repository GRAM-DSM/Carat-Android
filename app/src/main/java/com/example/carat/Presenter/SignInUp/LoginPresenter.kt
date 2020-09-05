package com.example.carat.Presenter.SignInUp

import com.example.carat.Model.TokenData
import com.example.carat.Repository.Repository
import com.example.carat.Util.BaseCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class LoginPresenter(var view: LoginContract.View) : LoginContract.Presenter,
    BaseCoroutineScope() {
    private val repository: Repository = Repository()

    override fun sendDataToServer() {
        CoroutineScope(coroutineContext).launch(handler) {
            val result = repository.doLogin()
            if (result.message != "") {
                view.showMessage(result.message)
            } else {
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