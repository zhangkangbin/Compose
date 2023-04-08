package com.compose.ui.login

import android.util.Log
import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginScreenViewModel : ScreenModel {
    private val _loginState = MutableStateFlow(LoginUIState())

    //只可读
    val loginState = _loginState.asStateFlow()

    // 事件,没有共享还是用MutableStateFlow。
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

     /*   repeat(2){
            delay(1000)
        }*/
        Log.d("mytest", "------end repeat---")

        _loginState.value.isShow=true
        _loginState.value.isJump=true
       // _event.emit(LoginEvent.LoginSuccess)





    }

}


class LoginUIState{
    var isShow=true;
    var isJump=false;
}
sealed interface LoginEvent {
    object LoginSuccess : LoginEvent
    object AccountNotExist : LoginEvent
    object PasswordInvalid : LoginEvent
    object Other : LoginEvent

}
