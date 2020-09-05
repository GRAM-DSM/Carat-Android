package com.example.carat.Model

data class UserData(
    var user_email: String = "",
    var name: String = "",
    var about_me: String = "",
    var profile_image_url: String = "",
    var cover_image_url: String = "",
    var following_count: Int = 0,
    var follower_count: Int = 0,
    var created_at: String = "",
    var myself: Boolean = false,
    var message: String = ""
)