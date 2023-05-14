package com.compose.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginScreenViewModel : ViewModel() {
    private val _loginState = MutableStateFlow(LoginUIState(false,LoginEvent.Other))

    //只可读
    val loginState = _loginState.asStateFlow()

    // 事件,没有共享还是用MutableStateFlow。
  //  private val _event = MutableSharedFlow<LoginEvent>();
  //  val event = _event.asSharedFlow()
    suspend fun login(name: String,password:String) {
        _loginState.value.isShow=false
          Log.d("mytest", "------ login---")
        if(name == "kang"){
            _loginState.value.isShow=true
            _loginState.value.loginEvent=LoginEvent.AccountNotExist
          //  _event.emit(LoginEvent.AccountNotExist)
            return
        }
        if(password == "123"){
            _loginState.value.isShow=true
            _loginState.value.loginEvent=LoginEvent.PasswordInvalid
           // _event.emit(LoginEvent.PasswordInvalid)
            return
        }

      repeat(2){
            delay(1000)
         // Log.d("mytest", "------ repeat---")
        }
      //  Log.d("mytest", "------end repeat---")

        _loginState.value.isShow=true
        _loginState.value.loginEvent=LoginEvent.LoginSuccess

        // _event.emit(LoginEvent.LoginSuccess)





    }

}


data class LoginUIState(
var isShow:Boolean,
var loginEvent:LoginEvent
)
sealed interface LoginEvent {
    object LoginSuccess : LoginEvent
    object AccountNotExist : LoginEvent
    object PasswordInvalid : LoginEvent
    object Other : LoginEvent

}
