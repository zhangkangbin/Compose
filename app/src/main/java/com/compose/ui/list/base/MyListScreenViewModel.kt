package com.compose.ui.list.base

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MyListScreenViewModel :BaseListScreenViewModel() {

    private val _ListData = MutableStateFlow(mutableListOf<BaseListScreenData>())

    val listData: StateFlow<MutableList<BaseListScreenData>> = _ListData.asStateFlow()


    init {
        val data=MutableList(15) { i -> BaseListScreenData(i, "Task # $i") }

        _ListData.value.addAll(data)
    }


     suspend fun addData(){

         Log.d("mytest","page:$mPage")
         var count=1;
         repeat(3){
             delay(1000)
             Log.d("mytest","-------------------------------${count++}-----------------page:$mPage")
         }
         Log.d("mytest","------------------------------add data-----------------page:$mPage")

         for (i in _ListData.value.size..(_ListData.value.size+5)){
             _ListData.value.add(BaseListScreenData(i, "Task # $i"))
         }
         mPage++;
         Log.d("mytest","------------_ListData size------------:${_ListData.value.size}")




    }
}