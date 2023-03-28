package com.compose.ui.list

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListScreenViewModel : ViewModel() {

    private val _itemsList = MutableStateFlow(UiState(list = Data.items))
    val itemsList = _itemsList.asStateFlow();//只读

    suspend fun addData() {

        Log.d("mytest", "1size${_itemsList.value.list.size}")
        _itemsList.value.refreshing = true
        delay(3000)
        _itemsList.update {
            val id = it.list.size + 1;
            for (i in id..id + 5) {
                it.list.add(ListScreenData(i, "Task # $i"))
            }
            _itemsList.value.refreshing = false
            Log.d("mytest", "2size${_itemsList.value.list.size}")
            return
        }
        Log.d("mytest", "3size${_itemsList.value.list.size}")


    }

}

data class UiState(
    var refreshing: Boolean = false,
    val list: MutableList<ListScreenData>
)

data class ListScreenData(
    val id: Int,
    val label: String,
    var checked: MutableState<Boolean> = mutableStateOf(false)
)

object Data {
    var items = arrayListOf(
        ListScreenData(
            0,
            "Lorem ipsum dolor sit ameercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
        ),
        ListScreenData(
            1,
            "Lorem ipsum dolor sit ameercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
        ),

        )


}