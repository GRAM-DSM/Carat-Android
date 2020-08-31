package com.example.carat.Model

data class ProfileTimeLinePost(
    var caring_id: String = "",
    var owner: OwnerData = OwnerData(),
    var post_time: String = "",
    var body: String = "",
    var body_images: ArrayList<String> = arrayListOf(),
    var carat_count: Int = 0,
    var recaring_count: Int = 0,
    var me_recaring: Boolean = false,
    var me_carat: Boolean = false
)