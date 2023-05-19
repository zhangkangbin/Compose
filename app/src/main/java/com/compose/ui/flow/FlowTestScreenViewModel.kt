package com.compose.ui.flow


import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.UUID

class FlowTestScreenViewModel :ViewModel() {

     val uiMessageManager = UiMessageManager()

    val state: StateFlow<DiscoverViewState> = combine(
        uiMessageManager.message,
    ) { message,
        ->
        DiscoverViewState(
            message = message,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = DiscoverViewState.Empty,
    )
    fun login(name:String){


        viewModelScope.launch {
            uiMessageManager.emitMessage(UiMessage(name))
        }


    }


}

data class DiscoverViewState(

    val message: UiMessage? = null,
) {


    companion object {
        val Empty = DiscoverViewState()
    }
}


class UiMessageManager{
    private val mutex = Mutex()

    private val _messages = MutableStateFlow(emptyList<UiMessage>())

    /**
     * A flow emitting the current message to display.
     */
    val message: Flow<UiMessage?> = _messages.map { it.firstOrNull() }.distinctUntilChanged()

    suspend fun emitMessage(message: UiMessage) {
        mutex.withLock {
            _messages.value = _messages.value + message
        }
    }

    suspend fun clearMessage(id: Long) {
        mutex.withLock {
            _messages.value = _messages.value.filterNot { it.id == id }
        }
    }

}
data class UiMessage(
    val message: String,
    val id: Long = UUID.randomUUID().mostSignificantBits,
)
