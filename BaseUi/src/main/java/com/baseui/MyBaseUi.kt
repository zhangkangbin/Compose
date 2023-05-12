package com.baseui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.launch

class MyBaseUi {

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun test(): Boolean {
        val bottomState = rememberBottomSheetScaffoldState()
        //BottomSheetScaffold()
        var showDialog by remember { mutableStateOf(false) }
        var refreshing by remember { mutableStateOf(false) }
        //val pullRefreshState = rememberPullRefreshState(refreshing, { viewModel.refresh() })

        return showDialog

    }

    @Composable
    fun MyDialog() {
        val showDialog = remember { MyDialogState(false) }

        MyDialogView(showDialog)

    }

    @Composable
    fun MyDialogView(showDialog2: MyDialogState) {
        //  var showDialog by remember { mutableStateOf(false) }
        val isShowState = rememberUpdatedState(MyDialogState(false))


        val scope = rememberCoroutineScope()
        //var showDialog2 = rememberCompositionContext()
        Button(onClick = {
            scope.launch {
                isShowState.value.isShow=true


            }

        }) {
            Text("Show Dialog:" + isShowState.value.isShow)
        }

        LaunchedEffect( isShowState.value.isShow) {

            Log.d("myTest", "LaunchedEffect.isShow=" +  isShowState.value.isShow)

        }

        val isAutoDismiss = true

        if (isShowState.value.isShow) {
            Dialog(
                onDismissRequest = {
                    if (isAutoDismiss) {
                        isShowState.value.isShow=false
                       // showDialog = MyDialogState(false)
                    }


                },
            ) {
                Column(
                    Modifier
                        .height(300.dp)
                        .fillMaxWidth()
                        .padding(15.dp)
                        .background(Color.White), horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "title", color = Color.Black,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                }

            }

        }

    }
}

class MyDialogState(var isShow: Boolean)