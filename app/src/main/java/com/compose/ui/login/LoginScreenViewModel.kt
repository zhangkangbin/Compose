package com.compose.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class LoginScreenViewModel : ViewModel() {
    private val _loginState = MutableStateFlow(false)
    private val _loginUiState = MutableStateFlow(0)
    private val _loginEvent =LoginEventUi()
    val loginEvent =_loginEvent.message
    val loginUiState =_loginUiState.asStateFlow()
    //只可读
    val loginState = _loginState.asStateFlow()


/*
    val state: StateFlow<LoginUIState> = combine(
        _loginState,
        _loginEvent.message,
        ::LoginUIState,

    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = LoginUIState.Empty,
    )
*/


    // 事件,没有共享还是用MutableStateFlow。
  //  private val _event = MutableSharedFlow<LoginEvent>();
  //  val event = _event.asSharedFlow()
    suspend fun login(name: String,password:String) {
        _loginState.value=true

          Log.d("mytest", "------ login---")
        if(name == "kang"){
            _loginState.value=false
           // _loginUiState.value=LoginUiState.AccountNotExist
            _loginEvent.emitMessage(LoginEvent.AccountNotExist)
         //   _loginState.value=LoginUIState(true,LoginEvent.AccountNotExist)
          //  _event.emit(LoginEvent.AccountNotExist)
            return
        }
        if(password == "123"){
          //  _loginUiState.value=LoginUiState.PasswordInvalid
            _loginEvent.emitMessage(LoginEvent.PasswordInvalid)
            //_loginState.value=LoginUIState(true,LoginEvent.PasswordInvalid)

            // _event.emit(LoginEvent.PasswordInvalid)
            return
        }

      repeat(2){
            delay(1000)
         // Log.d("mytest", "------ repeat---")
        }
        Log.d("mytest", "------LoginEvent LoginSuccess---")
        _loginState.value=true
       // _loginUiState.value=LoginUiState.LoginSuccess
        _loginEvent.emitMessage(LoginEvent.LoginSuccess)
       // _loginState.value=LoginUIState(true,LoginEvent.LoginSuccess)
        // _event.emit(LoginEvent.LoginSuccess)





    }

}

/*data class SearchViewState(
    val query: String = "",
    val searchResults: List<TiviShow> = emptyList(),
    val refreshing: Boolean = false,
    val message: UiMessage? = null,
) {
    companion object {
        val Empty = SearchViewState()
    }
}*/

data class LoginUIState(
var isShow:Boolean,
var loginEvent:LoginEventUi

){

    companion object {
        val Empty = LoginUIState(false,LoginEventUi())
    }
}

class LoginEventUi{

    private val mutex = Mutex()

    private val _messages = MutableStateFlow(emptyList<LoginEvent>())

    /**
     * A flow emitting the current message to display.
     */
    val message: Flow<LoginEvent?> = _messages.map { it.firstOrNull() }.distinctUntilChanged()

    suspend fun emitMessage(message: LoginEvent) {
        mutex.withLock {
            _messages.value = _messages.value + message
        }
    }


}


sealed interface LoginEvent {
    object LoginSuccess : LoginEvent
    object AccountNotExist : LoginEvent
    object PasswordInvalid : LoginEvent
    object Other : LoginEvent

}
