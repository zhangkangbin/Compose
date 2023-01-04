package com.compose.api

import com.compose.api.bean.AticleListBean
import retrofit2.Call
import retrofit2.http.GET


interface BaseApi {
    ///
    @GET("article/list/0/json")
    fun articleList(): Call<AticleListBean>;

    //fun getInternshipList(): Call<PreferentialActivitiesBean?>?
}