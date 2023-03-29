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
import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class LoginScreen :Screen{


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

        var tips by rememberSaveable(Unit) { mutableStateOf("tips") }
        LaunchedEffect(logViewModel.event ){

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
                        navigator.replaceAll(LoginSuccessScreen())
                        // navigator.pop(),至少保留一个

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

class LoginScreenViewModel : ViewModel() {
    private val _loginState = MutableStateFlow(LoginUIState())

    //只可读
    val loginState = _loginState.asStateFlow()

    // 事件
    private val _event = MutableSharedFlow<LoginEvent>();

    val event = _event.asSharedFlow()
    suspend fun login(name: String,password:String) {
        _loginState.value.isShow=false

        if(name == "kang"){
            _loginState.value.isShow=true
            _event.emit(LoginEvent.AccountNotExist)
            return
        }
        if(password == "123"){
            _loginState.value.isShow=true
            _event.emit(LoginEvent.PasswordInvalid)
            return
        }

        repeat(3) {
            delay(1000)

            Log.d("mytest", "------delay---$name   $password")
        }
        _loginState.value.isShow=true

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
class LoginUIState{
    var isShow=true;
}
sealed interface LoginEvent {
    object LoginSuccess : LoginEvent
    object AccountNotExist : LoginEvent
    object PasswordInvalid : LoginEvent
    object Other : LoginEvent

}
