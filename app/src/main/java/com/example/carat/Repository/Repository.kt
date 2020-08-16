package com.example.carat.Repository

import android.content.Context

class Repository(var context: Context) {
    val sharedPreferences = SharedPreferencesManager.getInstance(context)

    fun loginStatus(isLogin: Boolean) {
        sharedPreferences?.isLogin = isLogin
    }


}