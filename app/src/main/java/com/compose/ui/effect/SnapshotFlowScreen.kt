package com.compose.ui.effect

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.ui.list.ListScreenData

/**
 * 在LaunchedEffect中可以通过rememberUpdatedState获取最新状态，
 * 但是当状态发生变化时，LaunchedEffect无法第一时间收到通知，
 * 如果通过改变观察参数key来通知状态变化，则会中断当前执行中的任务，成本太高。
 * 简言之，LaunchedEffect缺少轻量级的观察状态变化的机制。
 *
 *
 * 当一个LaunchedEffect中依赖的State会频繁变化时，不应该使用State的值作为key，
 * 而应该将State本身作为key，然后在LaunchedEffect内部使用snapshotFlow依赖状态。
 * 使用State作为key是为了当State对象本身变化时重启副作用。
 *
 */
class SnapshotFlowScreen {

    @Preview
    @Composable
    fun SnapshotFlowScreenUi(){

        val task=getTasks();

        val stateList = rememberLazyListState()

        LazyColumn(state = stateList){
            items(task, key ={it.id}){
                ItemUi(it.id)
            }
        }


        //key 为val 不会变化。
        LaunchedEffect(stateList){
            Log.d("mytest","-LaunchedEffect------init--------")
            snapshotFlow {
                stateList.firstVisibleItemIndex
               // stateList.layoutInfo

            }.collect{

                //firstVisibleItemIndex 变化会回调这里。
                Log.d("mytest","-snapshotFlow------collect--------$it")
            }

        }


    }

    @Composable
    fun ItemUi(index:Int){

     Text(text = "item $index",
         Modifier
             .fillMaxWidth()
             .padding(15.dp))

    }

    private fun getTasks() = List(25) { i -> ListScreenData(i, "Task # $i") }
}