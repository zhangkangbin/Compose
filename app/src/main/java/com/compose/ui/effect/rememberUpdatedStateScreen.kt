package com.compose.ui.effect

import android.util.Log
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay

/**
 * rememberUpdatedState
 * LaunchedEffect会在参数key变化时启动一个协程，但有时我们并不希望协程中断，
 * 只要能够实时获取最新状态即可，此时可以借助rememberUpdatedState实现
 *
 * rememberUpdatedState用于保持参数的值在使用时是最新值
 *
 * rememberUpdatedState可以在不中断副作用的情况下感知外界的变化。
 */
class rememberUpdatedStateScreen {

    @Preview
    @Composable
    fun rememberUpdatedStateScreenUi(){

        items()

    }

    @Composable
    private fun items() {

        var data by remember{
            mutableStateOf("默认值")
        }

        Button(onClick = {
            Log.d("mytest","-----------修改值------------------")

            data="我是修改过后的值"

        }) {

            Text(text = data)
        }

        LaunchedEffect(Unit){
            Log.d("mytest","-----------rememberUpdatedStateScreen------------------")
            repeat(10){ i ->
                delay(1000)
                Log.d("mytest","$i-----------data:$data------------------")
            }
            Log.d("mytest","---最终的值：--------data:$data------------------")

        }
    }
}