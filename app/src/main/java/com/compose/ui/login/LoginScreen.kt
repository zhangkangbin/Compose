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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.compose.uitls.SharedPreferenceHelper
import kotlinx.coroutines.launch
import javax.inject.Inject


class LoginScreen :Screen{

  //  @Inject
   // lateinit var preference: SharedPreferenceHelper
    /**
     * 抽出所有的状态。无状态组件。
     */
    @Composable
    fun LoginScreenMainUi(userName:String,password: String,loginTipsText:String,
                          isShow: Boolean,tips:String,
                          userNameChange:(String)->Unit,
                          passwordChange:(String)->Unit,
                          login:()->Unit) {



        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                TextField(value = userName, onValueChange = {
                    userNameChange(it)
                })
                TextField(
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Next),
                    value = password,
                    visualTransformation = PasswordVisualTransformation(),
                    onValueChange = {

                        passwordChange(it)
                    }, modifier = Modifier.onFocusChanged {
                       Log.d("mytest"," it.hasFocus: ${ it.hasFocus}")
                       Log.d("mytest"," it.isFocused: ${ it.isFocused}")
                    })

                Text(text = tips)
                Button(modifier = Modifier.padding(top = 15.dp), onClick = {
                  login()
                }, enabled = isShow) {
                    Text(text = loginTipsText)
                }

            }


        }

    }

    @Preview
    @Composable
    override fun Content() {

        val logViewModel = LoginScreenViewModel()
        // 负责显示
        val state by logViewModel.loginState.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        if(state.isJump){
            Log.d("mytest", "------end isJump---")
            navigator.push(LoginSuccessScreen("kang"))
        }

        var tips by rememberSaveable(Unit) { mutableStateOf("tips") }
        LaunchedEffect(logViewModel.event){

            logViewModel.event.collect{
                when(it){
                    LoginEvent.AccountNotExist->{
                        Log.d("mytest","AccountNotExist")

                        tips="AccountNotExist"
                    }
                    LoginEvent.PasswordInvalid->{
                        Log.d("mytest","PasswordInvalid")
                        tips="PasswordInvalid"
                    }
                    LoginEvent.LoginSuccess->{
                        Log.d("mytest","LoginSuccess")
                        //replaceAll
                        navigator.push(LoginSuccessScreen("kang"))
                        // navigator.pop(),至少保留一个

                    //   preference.setString("userName","preference:Kang")

                    }else->{
                       Log.d("mytest","other")
                    }

                }
            }
        }

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


        LoginScreenMainUi(userName,password,loginTipsText,state.isShow,tips,{
            userName=it;
        },{
            password=it;
        }){

            scope.launch {
                loginTipsText = "Login...."
                logViewModel.login(userName,password)
                loginTipsText = "Login"
            }
        }
    }
}


