package com.example.carat.Presenter.SignInUp

import android.util.Log
import android.widget.Toast
import com.example.carat.Repository.Repository
import com.example.carat.Util.BaseCoroutineScope
import com.example.carat.Util.MyApp.Companion.context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CreateUserPresenter() :
    CreateUserContract.Presenter, BaseCoroutineScope(){

    private val repository: Repository = Repository()
    private val hashMap: HashMap<String, String> = HashMap()

    override fun sendDataToServer(name: String, email: String, password: String) {
        if(name != null && email != null && password != null){
            hashMap["name"] = name
            hashMap["email"] = email
            hashMap["password"] = password
        }

        CoroutineScope(coroutineContext).launch {
            val result = repository.signUp(hashMap)
            when(result.message) {
                "a bad request" -> {
                    Toast.makeText(context,"이메일이나 비밀번호를 다시 한번 확인해주세요",Toast.LENGTH_SHORT).show()
                }
                "That email is already in use." -> {
                    Toast.makeText(context,"이미 사용중인 이메일입니다.", Toast.LENGTH_SHORT).show()
                }
                " " -> {
                    Log.d("createUserPresenter","회원가입 성공")
                }
            }
        }

    }

}