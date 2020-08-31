package com.example.carat.Model

data class ProfileTimeLinePostData(
    var result: ArrayList<DetailTimeLineData> = arrayListOf(),
    var message: String = ""
)