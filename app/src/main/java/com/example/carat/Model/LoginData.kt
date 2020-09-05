package com.example.carat.Model

data class LoginData private constructor(
    var password: String = "",
    var email: String = ""
) {
    companion object {
        @Volatile
        private var instance: LoginData? = null
        @JvmStatic
        fun getInstance(): LoginData =
            instance ?: synchronized(this) {
                instance ?: LoginData().also {
                    instance = it
                }
            }
    }
}