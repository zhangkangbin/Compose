package com.compose.ui.effect

import android.util.Log
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 *
 * rememberCoroutineScope
 * LaunchedEffect虽然可以启动协程，但是LaunchedEffect只能在Composable中调用，
 * 如果想在非Composable环境中使用协程，例如在Button的OnClick中使用协程显示SnackBar，
 * 并希望其在OnDispose时自动取消，此时可以使用rememberCoroutineScope。
 */
class RememberCoroutineScopeScreen {

    @Preview
    @Composable
    fun  RememberCoroutineScopeUi(){
        var data by remember {
            mutableStateOf("我是默认值")
        }
        val scope=rememberCoroutineScope();

        Button(onClick = {
           // LaunchedEffect，在这块是没办法拿到的。所以要用rememberCoroutineScope
            scope.launch {
                Log.d("mytest","rememberCoroutineScope 去请求网络....")
                delay(2000)

                data="网络请求回来的值........"
            }
        }) {


            Text(text = "rememberCoroutineScope:$data")
        }
    }
}