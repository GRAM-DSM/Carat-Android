package com.example.carat.Util

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import retrofit2.HttpException

val handler = CoroutineExceptionHandler { _, throwable ->
    val tag = "Coroutine"
    val e: HttpException = throwable as HttpException
    when(e.code()) {
        400 -> {
            Log.e(tag, e.message())
        }
        401 -> {
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