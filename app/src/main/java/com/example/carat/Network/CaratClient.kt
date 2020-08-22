package com.example.carat.Network

import com.example.carat.Util.MyApp
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CaratClient {
    private const val baseUrl = "https://api.carat.com"

    val client = OkHttpClient.Builder()
        .addInterceptor(TokenAuthenticator(MyApp.context!!))
        .build()

    val caratApi: CaratService by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(client)
            .build()
            .create(CaratService::class.java)
    }
}