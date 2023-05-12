package com.compose.ui.atest

import android.util.Log
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview

class TestUi2 {

    private val TAG="mytest"

    @Preview
    @Composable
    fun Foo2() {
        var text by remember { mutableStateOf("") }
        Log.d(TAG, "Foo")
        Button(onClick = {
            text = "$text $text"
        }.also { Log.d(TAG, "Button") }) {
            Log.d(TAG, "Button content lambda")
            Text(text).also { Log.d(TAG, "Text") }
        }
        Log.d(TAG, "End")
    }


}