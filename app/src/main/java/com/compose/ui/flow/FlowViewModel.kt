package com.compose.ui.flow

import android.util.Log
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect


class FlowScreen{



    @Preview
    @Composable
    fun FlowScreenUi(){
        
        val viewModel=FlowViewModel()
        viewModel.test()

        var count by remember { mutableStateOf(0) }
       LaunchedEffect(Unit){

           viewModel.getEvent().collect{
               count=it.id
               Log.d("mytest","data:$it.id")
           }
       }

        Text(text = "Test:$count")
        

    }

}
data class FlowBean(val id:Int)
class FlowViewModel :ViewModel() {

    private val _event= MutableSharedFlow<FlowBean>()

    fun getEvent(): MutableSharedFlow<FlowBean> {

        return _event;
    }
    suspend fun postEvent(data:FlowBean){
        _event.emit(data)
    }

    fun test(){

        viewModelScope.launch {


            var count=0;
           while (true){

               delay(1000)

               postEvent(FlowBean(count++));
           }



        }

    }

    private fun simple(): Sequence<Int> = sequence {
        for (i in 1..3) {
            Thread.sleep(100)
            yield(i)
        }
    }
}