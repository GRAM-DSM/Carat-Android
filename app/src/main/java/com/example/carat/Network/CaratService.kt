package com.example.carat.Network

import com.example.carat.Model.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*
import java.util.*
import kotlin.collections.HashMap
import com.example.carat.Model.ServerMessage
import com.example.carat.Model.TokenData
import retrofit2.http.*

interface CaratService {
    @GET("user/auth")
    suspend fun reissueToken(@Header("Authorization") authorization: String): TokenData

    @POST ("/user")
    suspend fun signUp(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field ("password") password: String
    ) : ServerMessage

    @DELETE("/user")
    suspend fun deleteUser(
        @Header("Authorization") authorization: String
    ): ServerMessage

    @GET("/timeline")
    suspend fun getTimeLine(
        @Field("size") size: Int,
        @Field("last_caring_id") caring_id: Int
    )

    @GET("timeline/{email}")
    suspend fun getProfileTimeLine(
        @Path("email") email: String,
        @Header("Authorization") authorization: String,
        @Body body: HashMap<String, Int>
    ): PostData

    @POST("/recaring")
    suspend fun recaring(
        @Header("Authorization") Authorization: String,
        @Field("id") id: Int
    ) : ServerMessage

    @DELETE("/recaring")
    suspend fun deleteRecaring(
        @Header("Authorization") Authorization: String,
        @Field("id") caring_id: Int
    ) : ServerMessage

    @POST("/carat/<id>")
    suspend fun like(
        @Path("id") id: Int,
        @Header("Authorization") Authorization: String
    ) : ServerMessage

    @DELETE("/carat/<id>")
    suspend fun deleteCarat(
        @Path("id") id: Int,
        @Header("Authorization") Authorization: String
    ) : ServerMessage
  
    @GET("profile/{email}")
    suspend fun getProfile(
        @Header("Authorization") authorization: String,
        @Path("email") email: String
    ): UserData

    @GET("carat/{id}/list")
    suspend fun getCaratList(
        @Header("Authorization") authorization: String,
        @Path("id") id: String
    ): CaratData

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