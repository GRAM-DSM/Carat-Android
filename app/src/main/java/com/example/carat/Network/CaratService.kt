package com.example.carat.Network

import com.example.carat.Model.ServerMessage
import com.example.carat.Model.TokenData
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface CaratService {
    @POST("/user/auth")
    suspend fun login(@Field("password") password: String, @Field("email") email: String): TokenData

    @GET("/user/auth")
    suspend fun reissueToken(@Header("Authorization") authorization: String): ServerMessage
}