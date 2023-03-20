package com.compose.ui.effect

import android.media.Image
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay

/**
 * ProduceState
 * 任意类型的数据，都可以通过它转成State。
 * 只会执行一次，不会每次都执行。不像SIdeEffect。
 *
 * 可以用于给外界提供状态。
 */
class ProduceStateScreen {

    @Preview
    @Composable
    fun ProduceStateScreenUi(){
       // var timer by remember { mutableStateOf(0) }
        //只会执行一次，不会每次都执行。不像SIdeEffect
        //initialValue 为泛型
        val timer by produceState(initialValue = 0) {

            repeat(10){
                delay(1000)
                // repository.sendSignal()
                value++
                Log.d("mytest","produceState time....$value")
            }

        }
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {
            Log.d("mytest","update time....$timer")
            Text("Time $timer")
        }


    }


    }


