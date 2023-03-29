package com.compose.ui.state

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview

/**
 * 测试ui 重组范围。
 */
class UiState {

    private val TAG="mytest"

    @Preview
    @Composable
    fun Foo() {

        var text by remember { mutableStateOf("null") }
        var enabled by remember { mutableStateOf(true) }

        Button(enabled=enabled,onClick = { text = "$text $text";

            enabled=false
        }) {
            Log.d(TAG, "Button content lambda")
            Wrapper {
                Log.d(TAG, "B----Wrapper------")
                Text(text)
            }
        }
    }
    /**
     * 无状态组件，只在首次加载执行。状态发生改变这里也不会发生重组。
     */
    @Composable
    fun Wrapper(content: @Composable () -> Unit) {
        Log.d(TAG, "--------Wrapper recomposing--------------")
        Box {
            Log.d(TAG, "Box")
            content()
        }
    }
}