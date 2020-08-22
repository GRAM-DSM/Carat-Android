package com.example.carat.Network

import com.example.carat.Model.*
import retrofit2.Call
import retrofit2.http.*
import kotlin.collections.HashMap

interface CaratService {
    @GET("user/auth")
    suspend fun reissueToken(@Header("Authorization") authorization: String): TokenData

    @FormUrlEncoded
    @POST("caring")
    suspend fun createCaring(
        @Header("Authorization") authorization: String,
        @FieldMap image: HashMap<String, Any>,
        @Body caring: String
    ): CreateCaringData

    @GET("profile/{email}")
    suspend fun getProfile(
        @Header("Authorization") authorization: String,
        @Path("email") email: String
    ): UserData

    @FormUrlEncoded
    @PUT("profile")
    suspend fun modifyProfile(
        @Header("Authorization") authorization: String,
        @FieldMap parameter: HashMap<String, Any>
    ): Call<Unit>

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
}