package com.example.carat.Model

data class FollowData(
    var currentIndex: Int = 0,
    var num: Int = 0,
    var profile_image: String = "",
    var name: String = "",
    var email: String = "",
    var about_me: String = "",
    var following: Boolean = false
)