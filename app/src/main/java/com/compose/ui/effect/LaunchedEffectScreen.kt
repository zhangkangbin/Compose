package com.compose.ui.effect

import android.util.Log
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay

/**
 * LaunchedEffect
 * 当副作用中有处理异步任务的需求时，可以使用LaunchedEffect。
 * 在Composable进入OnActive时，LaunchedEffect会启动协程执行block中的内容，
 * 可以在其中启动子协程或者调用挂起函数。当Composable进入OnDispose时，协程会自动取消，
 * 因此LaunchedEffect不需要实现OnDispose{...}。
 */
class LaunchedEffectScreen {

    @Preview
    @Composable
    fun LaunchedEffectScreenUi(){

        var count by remember {
            mutableStateOf(0)
        }
        var data by remember {
            mutableStateOf("我是默认值")
        }
        Button(onClick = {
            //LaunchedEffect 监听的 key，发生变化，会再次启动协程去请求结果。
            count++;
            data="去再次请求网络！！"

        }) {

            Text(text = data)
        }

        //LaunchedEffect支持观察参数key的设置，当key发生变化时，当前协程自动结束，同时开启新协程。
        LaunchedEffect(count){
            Log.d("mytest","模拟耗时网络请求.....")
            delay(3000)
            data="我是网络请求的值！！$count"


        }

    }
}