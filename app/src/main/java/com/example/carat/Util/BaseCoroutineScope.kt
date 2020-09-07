package com.example.carat.Util

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class BaseCoroutineScope : CoroutineScope {
    val job = Job()
    override val coroutineContext: CoroutineContext   // 3
        get() = Main + job

    val handler = CoroutineExceptionHandler { _, throwable ->
        Log.e("CoroutineHandler", throwable.message.toString())
    }
}