package com.compose.ui.effect

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.compose.ui.list.ListScreenViewModel

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
        val count = remember {
            mutableStateOf(0)
        }

        //
        val result = remember {
            derivedStateOf {
                count.value++
            }
        }
        val viewModel= ListScreenViewModel()
        Scaffold {paddingValue->

            LazyColumn(Modifier.padding(paddingValue)){

                items(viewModel.tasks){
                    Text(text = "items:")
                }

            }

        }

    }




}

data class DerivedStateBean(var isCheck:Boolean, val name:String)


