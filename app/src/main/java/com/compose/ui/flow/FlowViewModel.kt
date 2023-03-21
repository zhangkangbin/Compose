package com.compose.ui.flow

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*


class FlowScreen{

    @Preview
    @Composable
    fun FlowScreenUi(){

        val viewModel=FlowViewModel()

        viewModel.test()


    }

}

class FlowViewModel :ViewModel() {


    fun test(){

            GlobalScope.launch {
                val withStr = withContext(Dispatchers.Default){
                    "a"
                }
                val awaitStr = async {
                    "b"
                }
                val list = simple()
                Log.d("test","withStr :$withStr")
                Log.d("test","awaitStr :${awaitStr.await()}")
                Log.d("test","list :$list  ")


        }

    }

    private fun simple(): Sequence<Int> = sequence {
        for (i in 1..3) {
            Thread.sleep(100)
            yield(i)
        }
    }
}