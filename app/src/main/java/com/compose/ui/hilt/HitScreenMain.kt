package com.compose.ui.hilt

import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

class HitScreenMain {

    @Preview
    @Composable
    fun testSingle(){

        //hilt 中viewModel 初始化
        val singleton: SingletonViewModel = viewModel()
        //val singleton= SingletonViewModel()
        singleton.postData()
    }
}