package com.example.carat.Model

data class CaratData(
    var result: ArrayList<CaratUnitData> = arrayListOf()
)

data class CaratUnitData(
    var id: String = "",
    var email: String = "",
    var image: String = "",
    var is_follower: Boolean = false
)