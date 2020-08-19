package com.example.carat.Model

data class UserData private constructor(
    var background: String = "",
    var profile: String = "",
    var name: String = "",
    var email: String = "",
    var introduction: String = "",
    var date: String = "",
    var following: String = "",
    var follower: String = "",
    var message: String = ""
) {
    companion object {
        @Volatile
        private var instance: UserData? = null
        @JvmStatic
        fun getInstance(): UserData =
            instance ?: synchronized(this) {
                instance ?: UserData().also {
                    instance = it
                }
            }
    }
}