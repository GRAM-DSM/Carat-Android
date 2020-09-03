package com.example.carat.Presenter.SignInUp

import android.widget.Toast
import com.example.carat.Repository.Repository
import com.example.carat.Repository.SharedPreferencesManager
import com.example.carat.Util.BaseCoroutineScope
import com.example.carat.Util.MyApp.Companion.context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class LoginPresenter(var view: LoginContract.View) : LoginContract.Presenter,
    BaseCoroutineScope(){

    private val sharedPreferencesManager: SharedPreferencesManager = TODO()

    private val repository: Repository = Repository()
    private val hashMap: HashMap<String, String> = HashMap()

    override fun sendDataToServer(email: String, password: String) {

        if(email != null && password != null) {
            hashMap["email"] = email
            hashMap["password"] = password
        }
        CoroutineScope(coroutineContext).launch {
            val result = repository.login(hashMap)
            when(result.message) {
                "The account does not exist" -> {
                    Toast.makeText(context,"계정이 존재하지 않습니다.",Toast.LENGTH_SHORT).show()
                }
                "Email and password do not match" -> {
                    Toast.makeText(context,"이메일이나 비밀번호를 다시 입력해주세요",Toast.LENGTH_SHORT).show()
                }
                else -> {
                    sharedPreferencesManager.saveToken = result.access_token
                }
            }

        }
    }
}