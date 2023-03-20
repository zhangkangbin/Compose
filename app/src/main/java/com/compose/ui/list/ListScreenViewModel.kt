package com.compose.ui.list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class ListScreenViewModel :ViewModel() {
    private val _tasks = getWellnessTasks().toMutableStateList()
    val tasks: List<ListScreenData>
        get() = _tasks

    fun remove(item: ListScreenData) {
        _tasks.remove(item)
    }
    private fun getWellnessTasks() = List(15) { i -> ListScreenData(i, "Task # $i") }
}


data class ListScreenData(val id: Int, val label: String,
                                 var checked: MutableState<Boolean> = mutableStateOf(false) )