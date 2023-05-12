package com.compose.http

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.Charset

class LogInterceptor : Interceptor{
    private val UTF8: Charset = Charset.forName("UTF-8")
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())
        val source = originalResponse.body?.source()
        source?.buffer//todo
        // Buffer the entire body.
        source?.request(Long.MAX_VALUE);
        val buffer = source?.buffer;
        val url = chain.request().url.toUrl().toString()
        //打印后台请求返回的Json
        Log.d("LogInterceptor", "URL:[" + url + "] ::: body: " + buffer?.clone()?.readString(UTF8));

        return originalResponse
    }

}
class HttpTool {

    companion object {
        val getIntance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            initRetrofit()
        }

        fun initRetrofit(): Retrofit {

            val client=OkHttpClient.Builder().addInterceptor(LogInterceptor()).build()

            return Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        }
    }


}