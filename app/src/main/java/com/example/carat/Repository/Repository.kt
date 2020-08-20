package com.example.carat.Repository

import android.content.Context
import com.example.carat.Model.UserObject

class Repository(var context: Context) {
    private val sharedPreferences = SharedPreferencesManager.getInstance(context)

    fun saveLoginState(isLogin: Boolean) {
        sharedPreferences?.isLogin = isLogin
    }

    fun saveToken(access: String, refresh: String) {
        sharedPreferences?.saveToken = access
        sharedPreferences?.saveRefreshToken = refresh
    }

    fun saveEmail() {
        sharedPreferences?.saveEmail = UserObject.getInstance().email
    }

    fun getLoginState(): Boolean? = sharedPreferences?.isLogin
    fun getAccess(): String? = sharedPreferences?.saveToken
    fun getRefresh(): String? = sharedPreferences?.saveRefreshToken
}