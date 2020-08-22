package com.example.carat.Model

data class TokenData private constructor(
    var access_token: String = "",
    var refresh_token: String = "",
    var message: String = ""
) {
    companion object {
        @Volatile
        private var instance: TokenData? = null
        @JvmStatic
        fun getInstance(): TokenData =
            instance ?: synchronized(this) {
                instance ?: TokenData().also {
                    instance = it
                }
            }
    }
}