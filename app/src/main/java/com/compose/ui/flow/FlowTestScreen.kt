package com.compose.ui.flow

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.compose.ui.login.LoginSuccessScreen
import kotlinx.coroutines.flow.collect

class FlowTestScreen : Screen {

    @Composable
    override fun Content(){
        val navigator = LocalNavigator.currentOrThrow
        val logViewModel = viewModel<FlowTestScreenViewModel>()

        logViewModel._uiMessageManager.let { message ->
            LaunchedEffect(message) {
               // snackbarHostState.showSnackbar(message.message)
                // Notify the view model that the message has been dismissed
                Log.d("mytest","message:${message.message.colle}")
            }
        }
        var userName by remember {
            mutableStateOf("user name")
        }
        Column() {

            Button(onClick = {

                navigator.push(LoginSuccessScreen(userName))

            }) {

            }


            LoginScreenMainUi("kk",{
                userName=it;

            }){

                logViewModel.login(userName)

            }

        }



    }
    /**
     * 抽出所有的状态。无状态组件。
     */
    @Composable
    fun LoginScreenMainUi(userName:String,
                          userNameChange:(String)->Unit,
                          login:()->Unit) {


        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Log.d("mytest","----------LoginScreenMainUi --Column-------")
                TextField(value = userName, onValueChange = {
                    userNameChange(it)
                })

              //  Text(text = tips)
                Button(modifier = Modifier.padding(top = 15.dp), onClick = {
                    login()
                }, enabled = true) {
                    Text(text = "login")
                }

            }


        }

    }

}