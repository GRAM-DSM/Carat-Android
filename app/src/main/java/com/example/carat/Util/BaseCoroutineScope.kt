package com.example.carat.Util

import android.util.Log
import com.example.carat.Model.TokenData
import com.example.carat.Network.CaratClient
import kotlinx.coroutines.*
import retrofit2.HttpException
import kotlin.coroutines.CoroutineContext

open class BaseCoroutineScope : CoroutineScope {
    val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO + handler

    val handler = CoroutineExceptionHandler { _, throwable ->
        val tag = "Coroutine"
        val e: HttpException = throwable as HttpException
        when(e.code()) {
            400 -> {
                Log.e(tag, e.message())
            }
            401 -> {
                processToken()
                Log.e(tag, e.message())
            }
            404 -> {
                Log.e(tag, e.message())
            }
            else -> {
                Log.e(tag, throwable.message())
            }
        }
    }

    private fun processToken() {
        CoroutineScope(coroutineContext).launch {
            CaratClient.caratApi.reissueToken(TokenData.getInstance().refresh_token)
        }
    }
}