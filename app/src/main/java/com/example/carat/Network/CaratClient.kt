package com.example.carat.Network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CaratClient {
    private const val baseUrl = "https://api.carat.com"

    val caratApi: CaratService by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(CaratService::class.java)
    }
}