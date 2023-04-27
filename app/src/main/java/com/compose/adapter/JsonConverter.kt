package com.compose.adapter

interface JsonConverter<T> {

   fun responseBodyConverter(string:String):T
}