package com.compose.ui.list.base

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BaseComposeListScreen2 {
    /**
     * 加载状态
     */

    private val Loading=1;
    private val Finish=2;

    @Preview
    @Composable
    fun TestListScreen22(){

        val listViewModel=MyListScreenViewModel()


        ListScreen(listViewModel);

    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun  ListScreen(listViewModel: MyListScreenViewModel) {

        val refreshScope = rememberCoroutineScope()
        var refreshing by remember { mutableStateOf(false) }
        var isShowLoadMore by remember(refreshScope) { mutableStateOf(false) }
        fun refresh() = refreshScope.launch {
            refreshing = true

            listViewModel.addData()
            refreshing = false

        }

        var defautLoadState by remember { mutableStateOf(0) };
        val stateList= rememberLazyListState()
        val state = rememberPullRefreshState(refreshing, ::refresh)

        fun loadMore() = refreshScope.launch {
            if (defautLoadState != Loading) {

                Log.d(
                    "mytest",
                    "----------LaunchedEffect----$defautLoadState--"
                )

                   // refreshing = true
                    defautLoadState = Loading;
                    isShowLoadMore = true
                    Log.d("mytest", "----------show more------")

                    listViewModel.addData()
                    isShowLoadMore = false
                  //   refreshing = false

                    defautLoadState = Finish;


                    Log.d("mytest", "----------hide more------")


            }

        }


        Box(Modifier.pullRefresh(state)) {
            LazyColumn(Modifier.fillMaxSize(),state=stateList) {


                items(listViewModel.listData.value, key = {it.id}) { it ->

                    ListItem(it.label)
                }

                item {


                        Box(
                            Modifier
                                .padding(20.dp)
                                .clickable {
                                   loadMore()
                                  //  loadMoreState.value
                                    Log.d("mytest", "-------test------")
                                }) {
                            Text(text = "加载中......")
                        }



                }
            }

            PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.TopCenter))
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