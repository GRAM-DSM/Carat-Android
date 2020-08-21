package com.example.carat.Model

data class FollowerData(
    var followers: ArrayList<FollowData> = arrayListOf(),
    var message: String = ""
)