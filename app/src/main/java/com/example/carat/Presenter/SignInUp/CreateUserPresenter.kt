package com.example.carat.Presenter.SignInUp

import com.example.carat.Repository.Repository
import com.example.carat.Util.BaseCoroutineScope
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
            repository.signUp(hashMap)
        }
    }

}