package com.example.carat.Model

data class UserData private constructor(
    var background: String? = null,
    var profile: String? = null,
    var name: String? = null,
    var email: String? = null,
    var introduction: String? = null,
    var date: String? = null,
    var following: String? = null,
    var follower: String? = null
) {
    companion object{
        @Volatile private var instance: UserData? = null
        @JvmStatic fun getInstance(): UserData =
            instance ?: synchronized(this) {
                instance ?: UserData().also {
                    instance = it
                }
            }
    }
}