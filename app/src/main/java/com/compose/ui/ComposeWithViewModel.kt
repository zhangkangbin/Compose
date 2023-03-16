package com.compose.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class ComposeWithViewModel :ViewModel() {

    private val _tasks = getWellnessTasks().toMutableStateList()
    val tasks: List<WellnessTask>
        get() = _tasks

    fun remove(item: WellnessTask) {
        _tasks.remove(item)
    }

}

private fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task # $i") }


class ComposeWithViewModelUi{

    @Preview
    @Composable
    fun ListMainView(){
        val wellnessViewModel: ComposeWithViewModel = viewModel()

        LazyColumn{

            //用key 防止数据错乱。
            items(items = wellnessViewModel.tasks, key = { it.id } ){
                ListState(it) {
                    wellnessViewModel.remove(it)
                }


            }
        }
    }
    @Composable
    fun  ListState(data: WellnessTask, tasks: () -> Unit){
        //var show by remember { mutableStateOf(false) }
        var show by rememberSaveable  { mutableStateOf(true) }
        ListItem(data.label,show,{ show = !show },tasks)
    }
    @Composable
    fun ListItem(label:String,isShow: Boolean, onCheckedChange: (Boolean) -> Unit,onClick:()->Unit){

        Row {
            Text(text = "viewModel checkBox:$label",Modifier.clickable(onClick=onClick) )
            Checkbox(checked =isShow , onCheckedChange =onCheckedChange)

            AnimatedVisibility(visible = isShow) {
                Text(text = "show something")
            }

        }

    }
}
