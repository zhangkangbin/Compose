package com.compose.http

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HttpTool {

    companion object {
        val getIntance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            initRetrofit()
        }

        fun initRetrofit(): Retrofit {

            return Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        }
    }


}