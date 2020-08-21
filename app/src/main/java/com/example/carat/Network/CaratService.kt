package com.example.carat.Network

import com.example.carat.Model.ServerMessage
import com.example.carat.Model.TokenData
import okhttp3.ResponseBody
import retrofit2.http.*
import javax.sql.StatementEvent

interface CaratService {
    @POST("/user/auth")
    suspend fun login(@Field("password") password: String, @Field("email") email: String): TokenData

    @GET("/user/auth")
    suspend fun reissueToken(@Header("Authorization") authorization: String): ServerMessage
}