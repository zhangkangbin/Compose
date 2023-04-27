package com.compose.adapter

import com.google.gson.Gson

class GsonConverter :JsonConverter<DataBean>{
    override fun responseBodyConverter(string: String): DataBean {
        val gson = Gson()
       // val type: Type = object : TypeToken<T>() {}.type
        return gson.fromJson(string, DataBean::class.java)
    }

}