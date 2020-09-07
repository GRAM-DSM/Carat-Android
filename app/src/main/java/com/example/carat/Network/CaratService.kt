package com.example.carat.Network

import com.example.carat.Model.*
import retrofit2.Call
import retrofit2.http.*
import kotlin.collections.HashMap
import com.example.carat.Model.TokenData
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface CaratService {
    @POST("user/auth")
    suspend fun doLogin(@Body body: RequestBody): TokenData

    @GET("user/auth")
    suspend fun reissueToken(@Header("Authorization") authorization: String): TokenData

    @POST("user/")
    suspend fun signUp(@Body body: RequestBody): Call<ServerMessage>


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
    ): Call<Void>

    @GET("/caring/detail/{id}")
    suspend fun detailCaring(@Path("id") id: String): DetailTimeLineData

    @POST("/recaring")
    suspend fun reCaring(
        @Header("Authorization") Authorization: String,
        @Body id: Int
    ): Call<Void>

    @DELETE("/recaring/{id}")
    suspend fun cancelReCaring(
        @Path("id") id: String,
        @Header("Authorization") Authorization: String
    ): Call<Void>

    @POST("/carat/{id}")
    suspend fun doCarat(
        @Path("id") id: String,
        @Header("Authorization") Authorization: String
    ): Call<Void>

    @DELETE("/carat/{id}")
    suspend fun cancelCarat(
        @Path("id") id: String,
        @Header("Authorization") Authorization: String
    ): Call<Void>

    @GET("recaring/{id}/list")
    suspend fun getCaringList(
        @Path("id") id: String,
        @Header("Authorization") authorization: String
    ): FollowingData

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
        @PartMap parameter: HashMap<String, MultipartBody.Part>,
        @Part("name") name: String,
        @Part("about_me") about_me: String
    ): Call<Void>

    @POST("profile/{email}/following")
    suspend fun doFollow(
        @Path("email") email: String,
        @Header("Authorization") authorization: String
    ): Call<Void>

    @DELETE("profile/{email}/following")
    suspend fun cancelFollow(
        @Path("email") email: String,
        @Header("Authorization") authorization: String
    ): Call<Void>

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