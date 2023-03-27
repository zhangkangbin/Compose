package com.compose.ui.login

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.rememberNavController
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class LoginScreenViewModel : ViewModel() {


    suspend fun login(name: String,password:String,onSuccess:()->Unit) {

        repeat(3) {

            delay(1000)
            Log.d("mytest", "------delay---$name   $password")
        }
        onSuccess()
    }

}

class LoginScreen :Screen{


    @Composable
    fun LoginScreenMainUi(logViewModel: LoginScreenViewModel) {

        val scope = rememberCoroutineScope();
        var userName by remember {
            mutableStateOf("user name")
        }
        var password by remember {
            mutableStateOf("")
        }

        var isEnabled by remember {
            mutableStateOf(true)
        }
        var loginTipsText by remember {
            mutableStateOf("Login")
        }

        val navigator = LocalNavigator.currentOrThrow
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                TextField(value = userName, onValueChange = {
                    userName = it
                })
                TextField(
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Next),
                    value = password,
                    visualTransformation = PasswordVisualTransformation(),
                    onValueChange = {

                        password = it
                    }, modifier = Modifier.onFocusChanged {

                       Log.d("mytest"," it.hasFocus: ${ it.hasFocus}")
                       Log.d("mytest"," it.isFocused: ${ it.isFocused}")
                       //Log.d("mytest"," it.isCaptured: ${ it.isCaptured}")
                    })

                Button(modifier = Modifier.padding(top = 15.dp), onClick = {

                    loginTipsText = "Login...."


                    isEnabled = false
                    scope.launch {

                        logViewModel.login(userName,password){
                            Log.d("mytest", "1onClick---${navigator.items.size}")

                           // navigator.pop(),至少保留一个
                            Log.d("mytest", "2onClick---${navigator.items.size}")
                            //replaceAll
                            navigator.replaceAll(LoginSuccessScreen())

                        }

                        isEnabled = true

                        loginTipsText = "Login"
                    }

                }, enabled = isEnabled) {

                    Text(text = loginTipsText)
                }

            }


        }

    }

    @Preview
    @Composable
    override fun Content() {

        val logViewModel = LoginScreenViewModel()

        LoginScreenMainUi(logViewModel)
    }
}

class LoginSuccessScreen :Screen{

    @Composable
    override fun Content() {
        Box(Modifier.fillMaxSize(),contentAlignment=Alignment.Center) {
            Text(text = "login success!")
        }

    }

}