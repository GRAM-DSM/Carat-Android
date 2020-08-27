package com.example.carat.Model

data class Pro(
    var recaring_owner: OwnerData = OwnerData(),
    var post_time: String = "",
    var recaring_id: String = "",
    var caring_id: String = "",
    var owner: OwnerData = OwnerData(),
    var body: String = "",
    var body_images: ArrayList<String> = arrayListOf(),
    var carat_count: Int = 0,
    var recaring_count: Int = 0,
    var me_recaring: Boolean = false,
    var me_carat: Boolean = false
)