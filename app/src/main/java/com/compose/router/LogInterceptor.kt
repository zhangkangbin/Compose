package com.compose.router

import android.util.Log

class LogInterceptor:RouterInterceptor {
    override fun routerInterceptor(chain: RouterInterceptor.Chain?): RouterResult? {
        Log.d("mytest","----LogInterceptor-------")
        val result=chain?.request()
        result?.router?.let { Log.d("mytest", it) }
        Log.d("mytest","----LogInterceptor-------")
        return result;
    }
}