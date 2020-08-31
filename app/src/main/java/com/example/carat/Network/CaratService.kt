package com.example.carat.Network

import com.example.carat.Model.*
import retrofit2.Call
import retrofit2.http.*
import kotlin.collections.HashMap
import com.example.carat.Model.ServerMessage
import com.example.carat.Model.TokenData
import okhttp3.MultipartBody

interface CaratService {
    @GET("user/auth")
    suspend fun reissueToken(@Header("Authorization") authorization: String): TokenData

    @POST("/user")
    suspend fun signUp(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): ServerMessage

    @DELETE("/user")
    suspend fun deleteUser(
        @Header("Authorization") authorization: String
    ): ServerMessage


    @GET("/timeline")
    suspend fun getTimeLine(@Body parameter: RequestCaringData): TimeLineData

    @GET("timeline/caring/{email}")
    suspend fun getCaringTimeLine(
        @Path("email") email: String,
        @Header("Authorization") authorization: String,
        @Body parameter: RequestCaringData
    ): ProfileTimeLinePostData

    @GET("timeline/carat/{email}")
    suspend fun getCaratTimeLine(
        @Path("email") email: String,
        @Header("Authorization") authorization: String,
        @Body parameter: RequestCaringData
    ): ProfileTimeLinePostData

    @Multipart
    @POST("/caring")
    suspend fun createCaring(
        @Header("Authorization") authorization: String,
        @Part image: List<MultipartBody.Part>,
        @Body caring: String
    ): ServerMessage

    @GET("/caring/detail/{id}")
    suspend fun detailCaring(@Path("id") id: String): DetailTimeLineData

    @POST("/recaring")
    suspend fun reCaring(
        @Header("Authorization") Authorization: String,
        @Body id: Int
    ): ServerMessage

    @DELETE("/recaring/{id}")
    suspend fun cancelReCaring(
        @Header("Authorization") Authorization: String,
        @Path("id") id: String
    ): ServerMessage

    @POST("/carat/{id}")
    suspend fun doCarat(
        @Path("id") id: String,
        @Header("Authorization") Authorization: String
    ): ServerMessage

    @DELETE("/carat/{id}")
    suspend fun cancelCarat(
        @Path("id") id: String,
        @Header("Authorization") Authorization: String
    ): ServerMessage

    @GET("carat/{id}/list")
    suspend fun getCaratList(
        @Path("id") id: String,
        @Header("Authorization") authorization: String
    ): CaratData


    @GET("profile/{email}")
    suspend fun getProfile(
        @Path("email") email: String,
        @Header("Authorization") authorization: String
    ): UserData

    @Multipart
    @PUT("profile")
    suspend fun modifyProfile(
        @Header("Authorization") authorization: String,
        @Part("cover_image") cover_image: MultipartBody.Part,
        @Part("profile_image") profile_image: MultipartBody.Part,
        @Part("name") name: String,
        @Part("about_me") about_me: String,
    ): Call<Unit>

    @POST("profile/{email}/following")
    suspend fun doFollow(
        @Path("email") email: String,
        @Header("Authorization") authorization: String
    ): Call<Unit>

    @DELETE("profile/{email}/following")
    suspend fun cancelFollow(
        @Path("email") email: String,
        @Header("Authorization") authorization: String
    ): Call<Unit>

    @GET("profile/{email}/following")
    suspend fun getFollowingList(
        @Path("email") email: String,
        @Header("Authorization") authorization: String
    ): FollowingData

    @GET("profile/{email}/followers")
    suspend fun getFollowersList(
        @Path("email") email: String,
        @Header("Authorization") authorization: String
    ): FollowerData
}