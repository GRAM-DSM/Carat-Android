package com.example.carat.Model

data class DetailTimeLineData(
    var recaring_name: String = "",
    var recaring_id: String = "0",
    var caring_id: String = "0",
    var owner: OwnerData = OwnerData(),
    var post_time: String = "",
    var body: String = "",
    var body_images: ArrayList<String> = arrayListOf(),
    var carat_count: Int = 0,
    var recaring_count: Int = 0,
    var am_i_recaring: Boolean = false,
    var am_i_carat: Boolean = false
)