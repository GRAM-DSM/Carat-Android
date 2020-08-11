package com.example.carat.Network

import com.example.carat.Model.TokenData
import okhttp3.ResponseBody
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

interface CaratService {
    @POST("/user/auth")
    suspend fun login(@Field("password") password: String, @Field("email") email: String) : TokenData

    @GET("/user/auth")
    suspend fun reissueToken() : ResponseBody
}