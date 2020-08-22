package com.example.carat.Network

import com.example.carat.Model.ServerMessage
import com.example.carat.Model.TokenData
import retrofit2.http.*

interface CaratService {
    @POST("/user/auth")
    suspend fun login(@Field("password") password: String, @Field("email") email: String): TokenData

    @GET("/user/auth")
    suspend fun reissueToken(@Header("Authorization") authorization: String): ServerMessage

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

    @GET("/carat/<id>/list")
    suspend fun caratList(
        @Path("id") id: Int,
        @Header("Authorization") Authorization: String
    )

}