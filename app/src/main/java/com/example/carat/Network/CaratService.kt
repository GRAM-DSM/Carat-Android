package com.example.carat.Network

import com.example.carat.Model.*
import retrofit2.Call
import retrofit2.http.*

interface CaratService {
    @GET("user/auth")
    suspend fun doLogout(@Header("Authorization") authorization: String): Call<Unit>

    @GET("user/refresh")
    suspend fun reissueToken(@Header("Authorization") authorization: String): TokenData

    @DELETE("user")
    suspend fun deleteAccount(@Header("Authorization") authorization: String): ServerMessage

    @GET("profile/{email}")
    suspend fun getProfile(
        @Header("Authorization") authorization: String,
        @Path("email") email: String
    ): UserData

    @GET("profile/{email}/following")
    suspend fun getFollowingList(
        @Header("Authorization") authorization: String,
        @Path("email") email: String
    ): FollowingData

    @GET("profile/{email}/followers")
    suspend fun getFollowersList(
        @Header("Authorization") authorization: String,
        @Path("email") email: String
    ): FollowerData

    @POST("profile/{email}/following")
    suspend fun doFollow(
        @Header("Authorization") authorization: String,
        @Path("email") email: String
    ): Call<Unit>

    @DELETE("profile/{email}/following")
    suspend fun cancelFollow(
        @Header("Authorization") authorization: String,
        @Path("email") email: String
    ): Call<Unit>
}