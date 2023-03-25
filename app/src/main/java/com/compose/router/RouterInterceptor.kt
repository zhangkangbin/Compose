package com.compose.router

interface RouterInterceptor {
    fun routerInterceptor(chain: Chain?): RouterResult?
    interface Chain {
        fun request(): RouterResult?
        fun proceed(request: RouterResult?): RouterResult?
       // val next: Int
    }
}