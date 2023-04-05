package com.compose.base.delegate

/**
 * 不支持多继承方式，所以通过接口方式，然后再委托，来实现多继承的效果。
 */
class TraceDelegateMainTest : TestZ(),TraceDelegateImpl by TraceDelegate() {


    fun initData(){

        getData()
        setEvent("login_event")

       // getLayoutId()
    }



}

open class TestZ{

    fun getData(): Int {

        return 17
    }
}