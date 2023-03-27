package com.compose.ui.list.base


import android.util.Log
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay

open class BaseListScreenViewModel :ViewModel() {
    internal var mPage=1;

    internal fun setPage(){
        mPage++;
    }
    internal fun getPage():Int{
        return mPage;
    }
    private val _tasks = getWellnessTasks()
    val tasks: List<BaseListScreenData>
        get() = _tasks


    suspend fun getData(){

        Log.d("mytest","page:$mPage")
        var count=1;
        repeat(3){
            delay(1000)
            Log.d("mytest","-------------------------------${count++}-----------------page:$mPage")
        }

        for (i in tasks.size..(tasks.size+5)){
            _tasks.add(BaseListScreenData(i, "Task # $i"))
        }
        mPage++;



    }
    fun remove(item: BaseListScreenData) {
        _tasks.remove(item)
    }
    private fun getWellnessTasks() = MutableList(15) { i -> BaseListScreenData(i, "Task # $i") }
}


data class BaseListScreenData(val id: Int, val label: String )