package com.example.carat.Presenter.SignInUp

import com.example.carat.Repository.Repository
import com.example.carat.Util.BaseCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class CreateUserPresenter(var view: CreateUserContract.View): CreateUserContract.Presenter,
    BaseCoroutineScope(){
    
    private val repository: Repository = Repository()

    override fun sendDataToServer(name: String, email: String, password: String) {
        val hashMap = HashMap<String,String>()

        CoroutineScope()
    }

}