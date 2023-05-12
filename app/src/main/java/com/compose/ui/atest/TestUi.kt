package com.compose.ui.atest

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class TestUi {

    private val TAG="mytest"

    @Preview
    @Composable
    fun Foo() {


            Box() {
                var text by remember { mutableStateOf("null") }
                var enabled by remember { mutableStateOf(true) }
                Log.d(TAG, "Foo")
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


        Log.d(TAG, "End")
    }



    @Composable
    fun Wrapper(content: @Composable () -> Unit) {
        Log.d(TAG, "--------Wrapper recomposing--------------")
        Box {
            Log.d(TAG, "Box")
            content()
        }
    }




}