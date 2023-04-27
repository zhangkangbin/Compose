package com.compose.adapter


class JsonHelper<T>(private val jsonConverter: JsonConverter<T>) {

    fun toJson(json: String): T {

        return jsonConverter.responseBodyConverter(json)

    }
}