package com.example.carat.Model

data class Post(
    var caring_id: Int = 0,
    var owner: OwnerData = OwnerData(),
    var post_time: String = "",
    var body: String = "",
    var body_images: ArrayList<String> = arrayListOf(),
    var is_retweet: Boolean = false,
    var retweet_refer: String = "",
    var carat_sum: Int = 0,
    var retweet_sum: Int = 0
)

data class OwnerData(
    var id: String = "",
    var profile_image: String = "",
)