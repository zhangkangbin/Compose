package com.compose.adapter

class MainTest {
}

fun main(){

    val json="{\\\"code\\\":1,\\\"msg\\\":\\\"kang\\\"}"

    val data=JsonHelper(GsonConverter()).toJson(json)
    JsonHelper(FastJsonConverter()).toJson(json)
    JsonHelper(object :JsonConverter<DataBean>{
        override fun responseBodyConverter(string: String): DataBean {
            return DataBean(1,"d")
        }
    }).toJson(json)
    println(data.msg)


}