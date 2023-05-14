package com.compose.ui.compositionLocal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

class CompositionLocalScreen {

    /**
     * compositionLocalOf在重组期间更改提供的值只会使读取其 current 值的内容无效。
     */
    private val LocalColor = compositionLocalOf { Color.Gray }

    /**
     * staticCompositionLocalOf：与 compositionLocalOf 不同，Compose 不会跟踪 staticCompositionLocalOf 的读取。
     * 更改该值会导致提供 CompositionLocal 的整个 content lambda 被重组，而不仅仅是在组合中读取 current 值的位置。
     *
     * 如果为 CompositionLocal 提供的值发生更改的可能性微乎其微或永远不会更改，使用 staticCompositionLocalOf 可提高性能。
     */
    private val LocalData= staticCompositionLocalOf { UserInfo("kk","1234")};

    private val LocalData2= compositionLocalOf { UserInfo("kk","1234")};
    private val LocalColor2 = staticCompositionLocalOf { Color.Blue };
    @Preview
    @Composable
    fun  CompositionLocalScreenUi(){

        //LocalColor.current 为蓝色
         CompositionLocalProvider {
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
        Text("1我是什么颜色的", Modifier.background(LocalColor.current))
    }
    @Preview
    @Composable
    fun LocalColorTextStatic() {

        Column() {
            Text("21我是什么颜色的${LocalData.current.name}",
                Modifier.background(LocalColor.current))

            Text("22我是什么颜色的${LocalData2.current.name}",
                Modifier.background(LocalColor2.current))
        }

    }
}

data class UserInfo(val name:String,val token:String)