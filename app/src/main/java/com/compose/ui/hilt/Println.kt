package com.compose.ui.hilt

import android.util.Log
import javax.inject.Inject

class Println @Inject constructor(){

    fun print(msg:String){

        Log.d("mytest",msg)
    }
}