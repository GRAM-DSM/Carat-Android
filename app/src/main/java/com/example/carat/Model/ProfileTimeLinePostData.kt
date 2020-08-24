package com.example.carat.Model

data class ProfileTimeLinePostData(
    var result: ArrayList<TimeLinePost> = arrayListOf(),
    var message: String = ""
)