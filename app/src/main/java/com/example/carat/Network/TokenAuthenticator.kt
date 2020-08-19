package com.example.carat.Network

import android.content.Context
import com.example.carat.Model.TokenData
import com.example.carat.Repository.SharedPreferencesManager
import com.example.carat.Util.MyApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Interceptor
import okhttp3.Response

class TokenAuthenticator(val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response? {
        val mainResponse: Response = chain.proceed(chain.request())

        if (mainResponse.code() == 401) {
            CoroutineScope(Dispatchers.Main).launch {
                val token = withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                        CaratClient.caratApi.reissueToken(TokenData.getInstance().refresh_token)
                    }.token

                SharedPreferencesManager(MyApp.context!!).saveToken = token
                TokenData.getInstance().access_token = token
            }
        }
        return mainResponse
    }
}