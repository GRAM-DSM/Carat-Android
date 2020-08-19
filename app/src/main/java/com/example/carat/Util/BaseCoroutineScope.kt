package com.example.carat.Util

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import retrofit2.HttpException
import kotlin.coroutines.CoroutineContext

open class BaseCoroutineScope : CoroutineScope {
    val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    val handler = CoroutineExceptionHandler { _, throwable ->
        val e: HttpException = throwable as HttpException

        when(e.code()) {
            200 -> Log.d("Handler", "200, 요청이 정상적으로 처리됨")
            201 -> Log.d("Handler", "201, 정상적으로 생성됨")
            204 -> Log.d("Handler", "204, 정상적으로 삭제됨")
            400 -> Log.e("Handler", "400, 요청이 이상함")
            401 -> Log.e("Handler", "401, 토큰이 만료되어 접근할 권한이 없음")
            403 -> Log.e("Handler", "403, 요청이 거부됨")
            404 -> Log.e("Handler", "404, 요청한 데이터가 없음")
            409 -> Log.e("Handler", "409, 해당 데이터가 이미 존재함")
        }
    }
}