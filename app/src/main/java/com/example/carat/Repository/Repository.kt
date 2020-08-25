package com.example.carat.Repository

import android.content.Context
import com.example.carat.Model.*
import com.example.carat.Network.CaratClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(var context: Context) {
    private val sharedPreferences = SharedPreferencesManager.getInstance(context)
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
    

}
