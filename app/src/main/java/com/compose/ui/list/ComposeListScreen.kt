package com.compose.ui.list

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.compose.R
import com.compose.ui.ComposeWithViewModel

class ComposeListScreen {
    /**
     * 加载状态
     */
    private val defaut=0;
    private val Loading=1;
    private val Finish=2;
    private val LoadFail=3;

    @Preview
    @Composable
    fun ListScreen(){
        val lstViewModel= ListScreenViewModel()

        val stateList= rememberLazyListState()

       // stateList.animateScrollToItem()
        LazyColumn(state = stateList){

            items(lstViewModel.tasks, key = {
                it.id
            }){

                ListItem("item:${it.label}")

                LaunchedEffect(key1 = Unit) {
                    val last=stateList.layoutInfo.visibleItemsInfo.lastOrNull()?.index
                    Log.d("mytest","index${last}")
                   // Log.d("mytest","index${stateList.firstVisibleItemIndex}")
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