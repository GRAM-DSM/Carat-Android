package com.example.carat.Model

data class PostData(
    var caring_id: Int,
    var post_time: String = "",
    var body: String = "",
    var is_retweet: Boolean = false,
    var retweet_refer: String = "",
    var carat_count: Int,
    var retweet_sum: Int,
    var message: String = ""
)