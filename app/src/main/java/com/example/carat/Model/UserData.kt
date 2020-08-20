package com.example.carat.Model

data class UserData(
    var name: String = "",
    var cover_image: String = "",
    var profile_image: String = "",
    var about_me: String = "",
    var following_count: Int = 0,
    var follower_count: Int = 0,
    var created_at: String = "",
    var myself: Boolean = false,
    var message: String = ""
)