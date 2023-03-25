package com.compose.router

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import okhttp3.OkHttpClient
import okhttp3.Route

/**
 * 测试入口
 */
class InterceptorComposeMain {


}
class InterceptorViewModel(navigation: NavHostController){

    private val navigation: NavHostController;


     init{

        this.navigation=navigation;
    }

    fun navigateSingleTopTo(route: String){

        val listInterceptor= listOf(LogInterceptor())


        val result=RouterResult(route,navigation)
        val navigationInterceptor=NavigationInterceptor(listInterceptor,result)

        navigationInterceptor.proceed(navigationInterceptor.request())


        val okHttpClient=OkHttpClient()

       // okHttpClient.newCall(null).enqueue()
    }

    private fun NavHostController.navigateSingleTopTo2(route: String) =
        this.navigate(route) { launchSingleTop = true }

}
