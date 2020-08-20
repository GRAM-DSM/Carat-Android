package com.example.carat.Network

import com.example.carat.Model.ServerMessage
import com.example.carat.Model.TokenData
import com.example.carat.Model.UserData
import retrofit2.Call
import retrofit2.http.*

interface CaratService {
    @POST("user/auth")
    suspend fun login(@Field("password") password: String, @Field("email") email: String): TokenData

    @GET("user/auth")
    suspend fun reissueToken(@Header("Authorization") authorization: String): ServerMessage

    @GET("profile/{email}")
    suspend fun getProfile(
        @Header("Authorization") authorization: String,
        @Path("email") email: String
    ): UserData

    @GET("profile/{email}/following")
    suspend fun getFollowingList(
        @Header("Authorization") authorization: String,
        @Path("email") email: String
    )

    @GET("profile/{email}/followers")
    suspend fun getFollowersList(
        @Header("Authorization") authorization: String,
        @Path("email") email: String
    )

    @POST("profile/{email}/following")
    suspend fun doFollow(
        @Header("Authorization") authorization: String,
        @Path("email") email: String
    ): Call<Void>

    @DELETE("profile/{email}/following")
    suspend fun cancelFollow(
        @Header("Authorization") authorization: String,
        @Path("email") email: String
    ): Call<Void>
}