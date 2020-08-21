package com.example.carat.Model

data class UserObject private constructor(
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
        private var instance: UserObject? = null
        @JvmStatic
        fun getInstance(): UserObject =
            instance ?: synchronized(this) {
                instance ?: UserObject().also {
                    instance = it
                }
            }
    }
}