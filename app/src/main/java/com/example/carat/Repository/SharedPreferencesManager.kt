package com.example.carat.Repository

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager private constructor(context: Context) {
    private val sharedPrefs: SharedPreferences = context.applicationContext.getSharedPreferences(
        MY_APP_PREFERENCES,
        Context.MODE_PRIVATE
    )

    var saveToken: String?
        get() = sharedPrefs.getString(SAVE_TOKEN, null)
        set(value) {
            val editor = sharedPrefs.edit()
            editor.putString(SAVE_TOKEN, value)
            editor.apply()
        }

    var saveRefreshToken: String?
        get() = sharedPrefs.getString(SAVE_REFRESH, null)
        set(value) {
            val editor = sharedPrefs.edit()
            editor.putString(SAVE_REFRESH, value)
            editor.apply()
        }

    var isLogin: Boolean
        get() = sharedPrefs.getBoolean(IS_LOGIN, false)
        set(value) {
            val editor = sharedPrefs.edit()
            editor.putBoolean(IS_LOGIN, value)
            editor.apply()
        }

    var saveEmail: String?
        get() = sharedPrefs.getString(SAVE_EMAIL, null)
        set(value) {
            val editor = sharedPrefs.edit()
            editor.putString(SAVE_EMAIL, value)
            editor.apply()
        }

    companion object {
        private const val MY_APP_PREFERENCES = "Carat-android"
        private const val IS_LOGIN = "isLogin"
        private const val SAVE_TOKEN = "saveToken"
        private const val SAVE_REFRESH = "saveRefresh"
        private const val SAVE_EMAIL = "saveEmail"
        private var instance: SharedPreferencesManager? = null

        @Synchronized
        fun getInstance(context: Context): SharedPreferencesManager? {
            if (instance == null) instance = SharedPreferencesManager(context)
            return instance
        }
    }
}