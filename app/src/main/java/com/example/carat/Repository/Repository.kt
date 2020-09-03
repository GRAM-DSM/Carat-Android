package com.example.carat.Repository

import android.content.Context
import com.example.carat.Model.*
import com.example.carat.Network.CaratClient
import com.example.carat.Util.MyApp.Companion.context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository() {
    private val sharedPreferences = context?.let { SharedPreferencesManager.getInstance(it) }
    private val api = CaratClient.caratApi

    fun saveLoginState(isLogin: Boolean) {
        sharedPreferences?.isLogin = isLogin
    }

    fun saveToken(access: String, refresh: String) {
        sharedPreferences?.saveToken = access
        sharedPreferences?.saveRefreshToken = refresh
    }

    fun saveEmail() {
        sharedPreferences?.saveEmail = UserData.getInstance().email
    }

    fun getLoginState(): Boolean? = sharedPreferences?.isLogin
    fun getAccess(): String? = sharedPreferences?.saveToken
    fun getRefresh(): String? = sharedPreferences?.saveRefreshToken

    suspend fun signUp(body: HashMap<String, String>): ServerMessage{
        return withContext(Dispatchers.IO){
            api.signUp(body)
        }
    }

    suspend fun login(body: HashMap<String, String>): TokenData{
        return withContext(Dispatchers.IO){
            api.login(body)
        }
    }

    suspend fun deleteUser():ServerMessage {
        return withContext(Dispatchers.IO) {
            api.deleteUser(TokenData.getInstance().access_token)
        }
    }




}
