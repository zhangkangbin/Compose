package com.compose.ui.list

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay

class ListViewViewModel< T :DataInfo> : ViewModel() {
    private val listData=  mutableStateListOf<T>()

  /*  init {

        for (data in 0 until 18){
            listData.add(DataInfo(data,"data:$data"))
        }
    }
*/

    suspend fun refresh(addInfo: (Int, String) -> T){
        delay(3000)
        listData.clear()
        val start=listData.size
        for (data in start until start+20){
            // listData.add(DataInfo(data,"data: $data"))
            listData.add(addInfo(data,"data: $data"))
        }
    }
    fun getList()=listData

    suspend fun getHttpData(url:String,addInfo:(Int,String)->T) {

        delay(2000)
        val start=listData.size
        for (data in start until start+20){
           // listData.add(DataInfo(data,"data: $data"))
            listData.add(addInfo(data,"data: $data"))
        }

    }



}

  open class DataInfo(_id: Int, _value: String){

      val id=_id
      val value=_value
  }

