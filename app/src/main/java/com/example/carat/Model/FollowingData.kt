package com.example.carat.Model

data class FollowingData(
    var followings: ArrayList<FollowData> = arrayListOf(),
    var message: String = ""
)