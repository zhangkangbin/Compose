package com.compose.ui.list

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

abstract class BaseListViewScreen<T:DataInfo>{

private val Loading = 1
private val Finish = 2
private var loadState = 0
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListViewScreenUi() {

    var isShowMore by remember { mutableStateOf(false) }

    //var isShowMore= rememberSaveable { mutableStateOf(false) }

    val viewViewModel: ListViewViewModel<T> = viewModel()
    val scope = rememberCoroutineScope()
    val stateList = rememberLazyListState()

    var hideLoadingPage by remember { mutableStateOf(true) }


    LaunchedEffect(Unit){


        delay(1000)

        hideLoadingPage=false
    }
    var refreshing by remember { mutableStateOf(false) }

    fun refresh() = scope.launch {
        refreshing = true

        viewViewModel.refresh { i, s -> addInfo(i,s) }
        refreshing = false

    }

    val pullRefreshState= rememberPullRefreshState(refreshing =refreshing , onRefresh = ::refresh)

    
    Box(
        Modifier
            .pullRefresh(pullRefreshState)
            .fillMaxSize()) {
        AnimatedVisibility(visible = hideLoadingPage) {
            Text(text = "loading", Modifier.fillMaxSize(), textAlign = TextAlign.Center)
        }
        LazyColumn(state = stateList) {

            items(viewViewModel.getList(), key = { it.id }) {

                GetItemView(it)

            }

            item {

                AnimatedVisibility(visible = isShowMore) {
                    Text(
                        text = "loading...",
                        modifier = Modifier
                            .padding(10.dp)
                    )
                }


            }


        }
        val isEnabledLoadMore by remember {
            derivedStateOf {
                //偏移量大于10，才会启用滚动到顶部的按钮。避免无意义的状态更新。
                var isLoadMore = stateList.firstVisibleItemIndex > 10 && loadState != Loading

                if (isLoadMore) {
                    val last = stateList.layoutInfo.visibleItemsInfo.lastOrNull()?.index


                    if (last != null) {
                        if (last > (viewViewModel.getList().size - 3)) {

                            isLoadMore = true
                        }
                    }

                }



                isLoadMore

            }
        }
        LaunchedEffect(isEnabledLoadMore) {

            snapshotFlow {
                stateList.firstVisibleItemIndex
                // stateList.layoutInfo

            }.collect {
                //  Log.d("mytest","1----------LoadState------$LoadState")
                //firstVisibleItemIndex 变化会回调这里。
                if (loadState == Loading) {
                    return@collect
                }

                scope.launch {
                    loadState = Loading
                    isShowMore = true

                    viewViewModel.getHttpData(getUrl()){id,value->
                        addInfo(id,value)
                    }
                    isShowMore = false
                    loadState = Finish
                }


                Log.d("mytest", "----------hide more------")
                Log.d("mytest", "-snapshotFlow------collect--------$it")
            }


        }

        PullRefreshIndicator(refreshing =refreshing , state = pullRefreshState,Modifier.align(
            Alignment.TopCenter))
    }



}
abstract fun addInfo(id:Int,value:String):T
abstract fun getUrl():String
@Composable
abstract fun GetItemView(dataInfo: T)
}