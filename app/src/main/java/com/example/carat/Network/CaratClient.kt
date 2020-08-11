package com.example.carat.Network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CaratClient {
    private const val baseUrl = "https://api.carat.com"

    var client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(AccessTokenInterceptor())
        .build()

    val caratApi: CaratService by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(client)
            .build().create(CaratService::class.java)
    }
}