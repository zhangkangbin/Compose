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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.launch



class LoginScreen() : Screen {

  //  @Inject
   // lateinit var preference: SharedPreferenceHelper
    
    @Preview(
        showBackground = true,
        showSystemUi = true)
    @Composable
    fun LoginScreenMainUiPreView(){

        var tips by remember {
            mutableStateOf("")
        }
        LoginScreenMainUi(
            userName=tips,
            password="kk",
            loginTipsText="kk",
            isShow=true,
            tips="tips",
            userNameChange={12

                tips=it

            },
            passwordChange={

            },
            login = {

            }
            )
    }

    /**
     * 抽出所有的状态。无状态组件。
     */
    @Composable
    fun LoginScreenMainUi(userName:String,
                          password: String,
                          loginTipsText:String,
                          isShow: Boolean,
                          tips:String,
                          userNameChange:(String)->Unit,
                          passwordChange:(String)->Unit,
                          login:()->Unit) {

        Log.d("mytest","----------LoginScreenMainUi --Composable-------")

        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Log.d("mytest","----------LoginScreenMainUi --Column-------")
                TextField(value = userName, onValueChange = {
                    userNameChange(it)
                })
                TextField(
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Next),
                    value = password,
                    visualTransformation = PasswordVisualTransformation(),
                    onValueChange = {

                        passwordChange(it)
                    })

                Text(text = tips)
                Button(modifier = Modifier.padding(top = 15.dp), onClick = {
                  login()
                }, enabled = true) {
                    Text(text = loginTipsText)
                }

            }


        }

    }

    @Preview
    @Composable
    override fun Content() {
        val logViewModel = viewModel<LoginScreenViewModel>()
       // val logViewModel = LoginScreenViewModel() 错误写法
       // val logViewModel: LoginScreenViewModel = viewModel()
        // 负责显示
        val state by logViewModel.loginState.collectAsState()
        val navigator = LocalNavigator.currentOrThrow


        logViewModel.loading=false
        var tips by rememberSaveable(Unit) { mutableStateOf("tips") }

        val loginUiState=logViewModel.loginUiState.collectAsState()
/*
        if(loginUiState.value!=LoginUiState.Init){

            LaunchedEffect(loginUiState.value){

                when(loginUiState.value){

                    LoginUiState.AccountNotExist->{
                        Log.d("mytest","AccountNotExist")

                        tips="AccountNotExist"
                    }
                    LoginUiState.PasswordInvalid->{
                        Log.d("mytest","PasswordInvalid")
                        tips="PasswordInvalid"
                    }
                    LoginUiState.LoginSuccess->{
                        Log.d("mytest","--LoginEvent LoginSuccess----")
                        //replaceAll
                        navigator.push(LoginSuccessScreen("kang"))
                        // navigator.pop(),至少保留一个
                        //   preference.setString("userName","preference:Kang")

                    }else->{
                    Log.d("mytest","other")
                }
                }
            }
        }*/

/*        LaunchedEffect(logViewModel.loginEvent){

            when(logViewModel.loginEvent){

                LoginEvent.AccountNotExist->{
                    Log.d("mytest","AccountNotExist")

                    tips="AccountNotExist"
                }
                LoginEvent.PasswordInvalid->{
                    Log.d("mytest","PasswordInvalid")
                    tips="PasswordInvalid"
                }
                LoginEvent.LoginSuccess->{
                    Log.d("mytest","--LoginEvent LoginSuccess----")
                    //replaceAll
                    navigator.push(LoginSuccessScreen("kang"))
                    // navigator.pop(),至少保留一个
                    //   preference.setString("userName","preference:Kang")

                }else->{
                Log.d("mytest","other")
            }
            }


        }*/

        val scope = rememberCoroutineScope()
        var userName by remember {
            mutableStateOf("user name")
        }
        var password by remember {
            mutableStateOf("")
        }

        var loginTipsText by remember {
            mutableStateOf("Login")
        }

        Column() {

        }
      //  Log.d("mytest","----------LoginScreenMainUi ---------${state.isShow}")

        LoginScreenMainUi(userName,password,
            loginTipsText,state,
            tips,{
            userName=it;
        },{
            password=it;
        }){

            Log.d("mytest","LoginScreenMainUi button")
            scope.launch {
               // loginTipsText = "Login...."
                logViewModel.login(userName,password)
                //loginTipsText = "Login"
            }
        }
    }
}


