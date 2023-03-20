package com.compose.ui.effect

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.ui.list.ListScreenViewModel
import kotlinx.coroutines.launch

/**
 * 文章
 * https://medium.com/androiddevelopers/jetpack-compose-when-should-i-use-derivedstateof-63ce7954c11b
 * derivedStateOf用来将一个或多个State转成另一个State。
 * derivedStateOf{...}的block中可以依赖其他State创建并返回一个DerivedState，
 * 当block中依赖的State发生变化时，会更新此DerivedState，
 * 依赖此DerivedState的所有Composable会因其变化而重组。
 *
 * remember(key)和之间的区别derivedStateOf在于重组的数量。
 * derivedStateOf {}当您的状态或键的变化超过您想要更新 UI 时使用。
 *
 * 避免无意义的重组和更新。
 */
class DerivedStateOfScreen {

    @Preview
    @Composable
    fun DerivedStateOfScreenUi(){


        val stateList = rememberLazyListState()
        val viewModel= ListScreenViewModel()

        val isEnabled by remember {
            derivedStateOf {
                //偏移量大于10，才会启用滚动到顶部的按钮。避免无意义的状态更新。
                stateList.firstVisibleItemIndex > 10
            }
        }

        val scope= rememberCoroutineScope()

        Scaffold(floatingActionButton = {

            Log.d("mytest","-------------避免无意义的组件重组。---update--------------")
            Button(enabled = isEnabled,onClick = {
                scope.launch {
                    Log.d("mytest","------------Button onClick---------")
                     stateList.animateScrollToItem(0,stateList.firstVisibleItemScrollOffset)

                }

            }) {
                Text(text = "Scroll to top")
            }

        }) {paddingValue->

            LazyColumn(Modifier.padding(paddingValue),state = stateList){

                items(viewModel.tasks, key = {
                    it.id
                }){


                    Text(text = "items:${it.id}",
                        Modifier
                            .padding(20.dp)
                            .fillMaxWidth())
                }

            }

        }

    }




}



