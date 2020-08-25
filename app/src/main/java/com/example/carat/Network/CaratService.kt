package com.example.carat.Network

import com.example.carat.Model.ServerMessage
import com.example.carat.Model.TokenData
import retrofit2.http.*

interface CaratService {
    @POST("/user/auth")
    suspend fun login(
        @Body password: String,
        @Body email: String
    ): TokenData

    @GET("/user/auth")
    suspend fun reissueToken(@Header("Authorization") authorization: String): ServerMessage
  
    @POST ("/user")
    suspend fun signUp(
        @Body name: String,
        @Body email: String,
        @Body password: String
    ) : ServerMessage

    @DELETE("/user")
    suspend fun deleteUser(
        @Header("Authorization") authorization: String
    ): ServerMessage

    @GET("/timeline")
    suspend fun getTimeLine(
        @Body size: Int,
        @Body caring_id: Int
    )

    @GET("/caring/detail/<id>")
    suspend fun detailCaring(
        @Path("id") id :String
    )

    @POST("/recaring")
    suspend fun recaring(
        @Header("Authorization") Authorization: String,
        @Body id: Int
    ) : ServerMessage

    @DELETE("/recaring")
    suspend fun deleteRecaring(
        @Path ("id") id: String,
        @Header("Authorization") Authorization: String
    ) : ServerMessage

    @POST("/carat/<id>")
    suspend fun like(
        @Path("id") id: String,
        @Header("Authorization") Authorization: String
    ) : ServerMessage

    @DELETE("/carat/<id>")
    suspend fun deleteCarat(
        @Path("id") id: String,
        @Header("Authorization") Authorization: String
    ) : ServerMessage

    @GET("/carat/<id>/list")
    suspend fun caratList(
        @Path("id") id: Int,
        @Header("Authorization") Authorization: String
    )

}