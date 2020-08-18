package com.example.carat.Network

import android.content.Context
import com.example.carat.Model.TokenData
import com.example.carat.Repository.SharedPreferencesManager
import com.example.carat.Util.MyApp
import okhttp3.*


class TokenAuthenticator constructor(val context: Context) : Authenticator {
    var sharedPrefManager: SharedPreferencesManager = SharedPreferencesManager(MyApp.context!!)

    fun intercept(chain: Interceptor.Chain): Response? {
        val mainResponse: Response = chain.proceed(chain.request())

        if (mainResponse.code() == 401) {
            val refreshToken = TokenData.getInstance().refresh_token
        }
        return mainResponse
    }

    override fun authenticate(route: Route?, response: Response): Request? {
        return response.request().newBuilder()
            .header("Authorization", TokenData.getInstance().access_token)
            .build();
    }

}
