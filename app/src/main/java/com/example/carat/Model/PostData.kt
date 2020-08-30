package com.example.carat.Model


data class PostData(
    var recaring_owner: Recaring_owner,
    var post_time: String = "",
    var recaring_id: String = "",
    var caring_id: Int,
    var owner: Owner,
    var post_time2: String = "",
    var body: String = "",
    var body_image: Body_image,
    var carat_count: Int,
    var recaring_count: Int,
    var me_recaring: Boolean = false,
    var me_carat: Boolean = true
    )

data class Recaring_owner(
    var id: String = "",
    var email: String = "",
    var profile_image: String = ""
)

data class Owner(
    var id: String = "",
    var email: String = "",
    var profile_image: String = ""
)

data class Body_image(
    var image1: String = "",
    var image2: String = "",
    var image3: String = ""
)
