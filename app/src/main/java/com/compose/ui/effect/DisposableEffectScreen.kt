package com.compose.ui.effect

import android.util.Log
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview

/**
 * DisposableEffect
 *可以感知组件的的生命周期
 * onActive and onDispose
 *
 * • 如果key为Unit或true这样的常量，则block只在OnActive时执行一次。
 *
 * 例如:用来注销回调，避免泄露,关闭资源。
 *
 * 新的副作用到来，即DisposableEffect因为key的变化再次执行。参数key也可以被认为是代表一个副作用的标识
 */
class DisposableEffectScreen {



    @Preview
    @Composable
    fun DisposableEffectScreenUi(){


        items{
            Log.d("mytest","------------处理退出资源，防止泄露-------------")


        }
    }

    @Composable
    fun items(destroy: () -> Unit){

        // var state =false 这样写，DisposableEffect是不会执行onActive的。
        var state by remember {
            mutableStateOf(false)
        }

        TextButton(onClick = {
            state=!state
            Log.d("mytest","------------onClick-------------$state")

        }) {
            Text(text = "click me.")


        }

        //val dispatcher=LocalOnBackPressedDispatcherOwner.current

        // 如果key为Unit或true这样的常量，则block只在OnActive时执行一次。
        DisposableEffect(state ){

            Log.d("mytest","------------onActive-------------")
            onDispose {
                destroy()
                Log.d("mytest","------------onDispose-------------")
            }

        }
    }
}