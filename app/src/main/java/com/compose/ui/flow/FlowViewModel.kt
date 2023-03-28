package com.compose.ui.flow

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class FlowScreen{

    @Preview
    @Composable
    fun UiTest3(){
        val viewModel=FlowViewModel()
        FlowScreenUi(viewModel)
    }

    @Composable
    fun FlowScreenUi(viewModel: FlowViewModel){


      // val count: Int by viewModel.stateFlow.collectAsState()
        val feedbackUIState by viewModel.stateFlow.collectAsState()
      //  val feedbackUIState by viewModel.stateFlow.collectAsState()

        Items(feedbackUIState){
            viewModel.changeData()
        };

    }

    @Composable
    fun Items(state: UiState, changeData:()->Unit){
        Column() {
            Text(text = "count:${state.data}")
            // viewModel.test()
            Button(onClick = {
                changeData()
                //count++;
            }) {


                Text(text = "test")
            }
        }

    }

}
@Preview
@Composable
fun UiTest2(){
    val viewModel=FlowViewModelInt()
    FlowScreenUi2(viewModel)
}

@Composable
fun FlowScreenUi2(viewModel: FlowViewModelInt){


    // val count: Int by viewModel.stateFlow.collectAsState()
    val feedbackUIState by viewModel.stateFlow.collectAsState()
    //  val feedbackUIState by viewModel.stateFlow.collectAsState()

    Items2(feedbackUIState){
        viewModel.changeData()
    };

}

@Composable
fun Items2(state: Int, changeData:()->Unit){
    Column() {
        Text(text = "count:${state}")
        // viewModel.test()
        Button(onClick = {
            changeData()
        }) {
            Text(text = "test")
        }
    }


}
class FlowViewModel :ViewModel() {
    private val mutableStateFlow = MutableStateFlow(UiState(1))//构造中需要有初始值
    //这里为什么要赋值给stateFlow呢，因为stateFlow是不能对value进行赋值的，
    // MutableStateFlow是可以的，这样做可以避免用户在activity中有更新数据的行为
   // val stateFlow: StateFlow<Int> = mutableStateFlow
    val stateFlow=mutableStateFlow.asStateFlow()
    fun changeData() {
        mutableStateFlow.value= UiState(22)
       // mutableStateFlow.value.count=9669;

        Log.d("mytest","ddd: ${mutableStateFlow.value}")
    }


}
class FlowViewModelInt :ViewModel() {
    private val mutableStateFlow = MutableStateFlow(1)//构造中需要有初始值
    //这里为什么要赋值给stateFlow呢，因为stateFlow是不能对value进行赋值的，
    // MutableStateFlow是可以的，这样做可以避免用户在activity中有更新数据的行为
    // val stateFlow: StateFlow<Int> = mutableStateFlow
    val stateFlow=mutableStateFlow.asStateFlow()
    fun changeData() {
        mutableStateFlow.value++
        // mutableStateFlow.value.count=9669;

        Log.d("mytest","ddd: ${mutableStateFlow.value}")
    }


}

data class UiState(var data:Int=1)