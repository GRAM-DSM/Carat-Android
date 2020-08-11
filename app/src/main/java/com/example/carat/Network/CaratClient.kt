package com.example.carat.Network

import com.example.carat.Model.TokenData
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CaratClient {
    private const val baseUrl = "https://api.carat.com"

    var client: OkHttpClient = OkHttpClient.Builder()
    .addInterceptor { chain ->
        val newRequest: Request  = chain.request().newBuilder()
            .addHeader("Authorization", TokenData.access_token)
            .build()
        chain.proceed(newRequest)
    }.build()

    val caratApi: CaratService by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(client)
            .build().create(CaratService::class.java)
    }
}