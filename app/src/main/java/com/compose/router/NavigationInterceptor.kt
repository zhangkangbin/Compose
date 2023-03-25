package com.compose.router

import android.util.Log


/**
 * 路由跳转拦截器
 */
internal class NavigationInterceptor(interceptorList: List<RouterInterceptor>, result: RouterResult) :
    RouterInterceptor.Chain {


    private val interceptorList: List<RouterInterceptor>
    private val result: RouterResult
    //override val next: Int

    init {
        this.interceptorList = interceptorList
       // this.next = next
        this.result = result
    }


    var next=0;
    override fun request(): RouterResult {
        return result
    }

    override fun proceed(request: RouterResult?): RouterResult? {
        if(request==null){
            return null
        }

        Log.d("mytest","----NavigationInterceptor-------")


        val mainInterceptor = NavigationInterceptor(interceptorList, request)

        val interceptor = interceptorList[next]

        next++

        val routerInterceptor=interceptor.routerInterceptor(mainInterceptor)


        //处理一些意外情况，比如路由被上一个拦截器处理了。
        request.navigation.navigate(request.router){
            launchSingleTop=true
        }

        //执行下一个interceptor
        //  System.out.println("这里next--" + next);
        Log.d("mytest","----NavigationInterceptor-------")

        return routerInterceptor;
    }


}
