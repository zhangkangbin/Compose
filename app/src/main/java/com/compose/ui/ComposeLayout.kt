package com.compose.ui

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.R


class ComposeLayout {

    @Preview
    @Composable
    fun body() {

        val numbers = mutableListOf(0,8,5,5,50,9,99,1,2,4)

            Scaffold(
                bottomBar = { SootheBottomNavigation() }
            ) { padding ->
                Column (   Modifier.verticalScroll(rememberScrollState())){
                    SearchBar()
                    LazyRow(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {

                        items(numbers){
                            BodyElement()
                        }


                    }

                    Row(
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .background(Color.Gray)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {

                        Text(
                            "AA",
                            Modifier
                                .padding(16.dp)
                                .weight(1f), textAlign = TextAlign.Center
                        )
                        Text(
                            "BB",
                            Modifier
                                .padding(16.dp)
                                .weight(1f), textAlign = TextAlign.Center
                        )
                    }

                    LazyHorizontalGrid(
                        rows = GridCells.Fixed(2),
                        modifier   = Modifier.height(200.dp)
                    ) {
                        items(numbers) { item ->
                            CardListItem(item)
                        }
                    }

                    HomeContent(title = "活动详情",Modifier.padding(10.dp)){

                        CardListItem(1)
                        CardListItem(2)
                        CardListItem(2)
                        CardListItem(2)
                    }

                    HomeContent(title = "商品列表",Modifier.padding(10.dp)){

                        CardListItem(1)
                        CardListItem(2)
                        CardListItem(2)
                        CardListItem(2)
                        CardListItem(2)
                        CardListItem(2)
                    }
                }
            }



    }

    @Preview
    @Composable
    fun SearchBar(modifier: Modifier = Modifier) {

        TextField(
            value = "",
            onValueChange = {
                Log.d("mytest", it)
            },
            modifier
                .fillMaxWidth()
                .padding(15.dp)
                .heightIn(min = 56.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface
            ),
            placeholder = {
                Text(stringResource(R.string.app_tips))
            },
        )

    }

    @Preview
    @Composable
    fun BodyElement() {

        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(R.drawable.ab1_inversions),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(88.dp)
                    .clip(CircleShape)
            )
            Text(text = "jackson", Modifier.padding(top = 24.dp))
        }

    }


    @Composable
    fun CardListItem(index: Int) {

        Surface(shape = MaterialTheme.shapes.small, modifier = Modifier.padding(6.dp)) {
            Row(
                Modifier
                    .width(192.dp)
                    ) {

                Image(
                    painter = painterResource(id = R.drawable.img),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(56.dp)
                )

                Text(text = "Nature..$index", Modifier.padding(6.dp))
            }
        }
    }

    /**
     * 模块坑位。TopAppBar 为 title、navigationIcon 和 actions 提供槽位。
     */
    @Composable
    fun HomeContent(title:String,modifier: Modifier=Modifier,content: @Composable () -> Unit){
        Column(modifier) {
            Text(text = title,    style = MaterialTheme.typography.h6,)
            content()//模块坑位
        }

    }


    @Composable
    private fun SootheBottomNavigation(modifier: Modifier = Modifier) {
        BottomNavigation(modifier,  backgroundColor = MaterialTheme.colors.background,) {
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(R.string.bottom_navigation_home))
                },
                selected = true,
                onClick = {}
            )
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(R.string.bottom_navigation_profile))
                },
                selected = false,
                onClick = {}
            )
        }
    }
}