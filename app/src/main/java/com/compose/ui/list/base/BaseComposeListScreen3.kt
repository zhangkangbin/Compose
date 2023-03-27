package com.compose.ui.list.base

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.R

class BaseComposeListScreen3 {
    /**
     * 加载状态
     */

    private val Loading=1;
    private val Finish=2;
    //private val LoadFail=3;

  
    @Preview
    @Composable
    fun TestListScreen3(){

        val listViewModel=MyListScreenViewModel()
        val stateList= rememberLazyListState()


        ListScreen(listViewModel,stateList);

    }

    @Composable
    fun  ListScreen(
        listViewModel: MyListScreenViewModel,
        stateList: LazyListState
    ){

        var defautLoadState by rememberSaveable { mutableStateOf(0) };
        // val isShowLoadMore = rememberSaveable { mutableStateOf(false) }
        var isShowLoadMore by rememberSaveable { mutableStateOf(false) }




        LazyColumn(state = stateList){
            Log.d("mytest","----------LazyColumn------")


            if(defautLoadState!=Loading){

                items(listViewModel.tasks, key = {
                    it.id
                }){

                    ListItem("item:${it.label}")

                    LaunchedEffect(stateList) {

                        snapshotFlow {
                            stateList.firstVisibleItemIndex
                            // stateList.layoutInfo

                        }.collect{

                            //firstVisibleItemIndex 变化会回调这里。
                            val last= stateList.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: return@collect
                            if(defautLoadState==Loading){
                                return@collect
                            }
                            if(last>(listViewModel.tasks.size-3)){

                                defautLoadState=1;
                                isShowLoadMore=true
                                Log.d("mytest","----------show more------")
                                listViewModel.getData();
                                isShowLoadMore=false

                                defautLoadState=Finish;
                                Log.d("mytest","----------hide more------")

                                Log.d("mytest","----------index${last}")
                            }
                            //  Log.d("mytest","-snapshotFlow------collect--------$it")
                        }


                    }


                }
            }


            
            item {

                if(isShowLoadMore){
                    Box(Modifier.padding(20.dp)) {
                        Text(text = "加载中......")
                    }
                }


            }
        }


    }

    @Composable
    fun ListItem(text:String){

        Row(
            Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()) {
            Image(painter = painterResource(id = R.drawable.img), contentDescription = null,
                Modifier
                    .width(50.dp)
                    .height(50.dp))
            Text(text =text ,Modifier.padding(8.dp))
        }
    }
}