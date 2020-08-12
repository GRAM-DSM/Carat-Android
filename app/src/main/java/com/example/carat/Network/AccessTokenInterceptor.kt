package com.example.carat.Network

import com.example.carat.Model.TokenObject
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AccessTokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = newRequestWithAccessToken(chain.request(), TokenObject.access_token)
        return chain.proceed(request)
    }

    private fun newRequestWithAccessToken(request: Request, accessToken: String): Request {
        return request.newBuilder()
            .header("Authorization", accessToken)
            .build()
    }
}