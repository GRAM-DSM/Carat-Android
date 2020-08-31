package com.example.carat.Model

data class CaratData(
    var result: ArrayList<CaratUnitData> = arrayListOf(),
    var message: String
)

data class CaratUnitData(
    var id: String = "",
    var email: String = "",
    var image: String = "",
    var is_follower: Boolean = false
)