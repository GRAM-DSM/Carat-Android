package com.example.carat.Repository

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager private constructor(context: Context) {
    private val sharedPrefs: SharedPreferences = context.applicationContext.getSharedPreferences(
        MY_APP_PREFERENCES,
        Context.MODE_PRIVATE
    )
    var isLogin: Boolean
        get() = sharedPrefs.getBoolean(IS_LOGIN, false)
        set(value) {
            val editor = sharedPrefs.edit()
            editor.putBoolean(IS_LOGIN, value)
            editor.apply()
        }

    companion object {
        private const val MY_APP_PREFERENCES = "Carat-android"
        private const val IS_LOGIN = "isLogin"
        private var instance: SharedPreferencesManager? = null

        @Synchronized
        fun getInstance(context: Context): SharedPreferencesManager? {
            if (instance == null) instance = SharedPreferencesManager(context)
            return instance
        }
    }
}