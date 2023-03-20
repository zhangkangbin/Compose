package com.compose.ui.effect

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

/**
 * SIdeEffect
 * 在每次成功重组时都会执行，所以不能用来处理那些耗时或者异步的副作用逻辑。
 *
 * 既然每次重组都执行，那么和直接在Composable中写有什么区别？
 * 重组会触发Composable重新执行，但是重组不一定会成功结束，
 * 有的重组可能会中途失败。SideEffect仅在重组成功时才会执行.
 */
class SideEffectScreen {

    @Preview
    @Composable
    fun SideEffectScreenUi(){

        var timer by remember { mutableStateOf(0) }

        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {
            Log.d("mytest","update time....$timer")
            Text("Time $timer")
        }

        SideEffect {
            Thread.sleep(1000)

            //这里更新了，导致组件重组，然后继续进来，循环下去。
            timer++
        }
    }
}