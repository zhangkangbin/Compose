package com.compose.ui.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class Data{
    var userName by mutableStateOf("")
    var password by mutableStateOf("")
    var loginTipsText by mutableStateOf("")

}
interface ClickType{

    object Login :ClickType
    object ForgetPassword :ClickType

}
interface DataChange{

    // get() = mutableStateOf("")

    object Login :ClickType
    object ForgetPassword :ClickType

}
interface DataType{

    object UserName :DataType
    object Password :DataType
    object LoginTips :DataType
}

fun String.log(){

    Log.d("mytest",this)
}