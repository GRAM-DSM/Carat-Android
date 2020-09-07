package com.example.carat.Util

import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import kotlin.coroutines.CoroutineContext

open class BaseCoroutineScope : CoroutineScope {
    val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Main + job

    val handler = CoroutineExceptionHandler { _, throwable ->
        Log.e("CoroutineHandler", throwable.message.toString())
    }
}