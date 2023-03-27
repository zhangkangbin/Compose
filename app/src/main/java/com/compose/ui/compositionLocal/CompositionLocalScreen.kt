package com.compose.ui.compositionLocal

import androidx.compose.foundation.background
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

class CompositionLocalScreen {

    private val LocalColor = compositionLocalOf { Color.Black };


    @Preview
    @Composable
    fun  CompositionLocalScreenUi(){

        //LocalColor.current 为蓝色
         CompositionLocalProvider(LocalColor provides Color.Blue) {
             LocalColorText()
        }
        //LocalColor.current is Red
        CompositionLocalProvider(LocalColor provides Color.Red) {
            LocalColorText()
        }
    }

    @Preview
    @Composable
    fun LocalColorText() {
        Text("我是什么颜色的", Modifier.background(LocalColor.current))
    }
}