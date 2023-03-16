package com.compose.ui


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class ComposeState {

    @SuppressLint("UnrememberedMutableState")
    @Composable
    fun counter(modifier: Modifier = Modifier){

        Column(modifier = modifier.padding(16.dp)) {
            // Changes to count are now tracked by Compose
            // val count: MutableState<Int> = mutableStateOf(0)
            // val count: MutableState<Int> = remember { mutableStateOf(0) }
            val count: MutableState<Int> = rememberSaveable { mutableStateOf(0) }

            Text("You've had ${count.value} glasses.")
            Button(onClick = {
                Log.d("mytest","button.........")
                count.value++
            }, Modifier.padding(top = 8.dp)) {
                Text("Add one")
            }
        }
    }

    @Composable
    fun WaterCounter(modifier: Modifier = Modifier) {
        Column(modifier = modifier.padding(16.dp)) {
            var count by remember { mutableStateOf(0) }
            if (count > 0) {
                var showTask by remember { mutableStateOf(true) }
                if (showTask) {
                    WellnessTaskItem(
                        onClose = { showTask = false },
                        taskName = "Have you taken your 15 minute walk today?"
                    )
                }
                Text("You've had $count glasses.")
            }

            Row(Modifier.padding(top = 8.dp)) {
                Button(onClick = { count++ }, enabled = count < 10) {
                    Text("Add one")
                }
                Button(onClick = { count = 0 }, Modifier.padding(start = 8.dp)) {
                    Text("Clear water count")
                }
            }
        }
    }

    @Composable
    fun WellnessTaskItem(
        taskName: String,
        onClose: () -> Unit,
        modifier: Modifier = Modifier
    ) {
        Row(
            modifier = modifier, verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp),
                text = taskName
            )
            IconButton(onClick = onClose) {
                Icon(Icons.Filled.Close, contentDescription = "Close")
            }
        }
    }


    /**
     * 有状态与无状态
     */
//无状态
    @Composable
    fun StatelessCounter(count: Int, onIncrement: () -> Unit, modifier: Modifier = Modifier) {
        Column(modifier = modifier.padding(16.dp)) {
            if (count > 0) {
                Text("You've had $count glasses.")
            }
            Button(onClick = onIncrement, Modifier.padding(top = 8.dp), enabled = count < 10) {
                Text("Add one")
            }
        }
    }
    /**
     * 有状态
     */
    @Composable
    fun StatefulCounter(modifier: Modifier = Modifier) {
        var count by rememberSaveable { mutableStateOf(0) }
        StatelessCounter(count, { count++ }, modifier)
    }

    /**
     * 组合无状态和有状态,二者相结合。
     */
    @Preview
    @Composable
    fun WellnessScreen(modifier: Modifier = Modifier) {
        StatefulCounter(modifier)
    }

    private fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task # $i") }

    @Preview
    @Composable
    fun ListMainView(){
        val tasks = remember { getWellnessTasks().toMutableStateList()}
        LazyColumn{

            //用key 防止数据错乱。
            items(items = tasks, key = { it.id } ){
                ListState(it,tasks)


            }
        }
    }
    @Composable
    fun  ListState(data: WellnessTask, tasks: SnapshotStateList<WellnessTask>){
        //var show by remember { mutableStateOf(false) }
        var show by rememberSaveable  { mutableStateOf(true) }
        ListItem(data.label,show,{ show = !show },{
            tasks.remove(data)
        })
    }
    @Composable
    fun ListItem(label:String,isShow: Boolean, onCheckedChange: (Boolean) -> Unit,onClick:()->Unit){

        Row {
            Text(text = "checkBox:$label",Modifier.clickable(onClick=onClick) )
            Checkbox(checked =isShow , onCheckedChange =onCheckedChange)

            AnimatedVisibility(visible = isShow) {
                Text(text = "show something")
            }

        }

    }
}



data class WellnessTask(val id: Int, val label: String)