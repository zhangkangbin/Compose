package com.compose.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import retrofit2.http.Body

class ComposeTopBar {

    @Composable
    fun LayoutStudy() {
        Scaffold(topBar = {
            TopAppBar(
                title = { Text(text = "LayoutStudy") },
                actions = {
                    IconButton(
                        onClick = { },

                        ) {
                        Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
                    }
                }
            )
        }) { innerPadding ->

            Column(Modifier.padding(innerPadding)){

            }
            //BodyUI(Modifier.padding(innerPadding))
        }
    }

    @Composable
    fun BodyUI(modifier: Modifier = Modifier){
        Column(modifier = modifier.padding(8.dp)) {
            Text(text = "Hi there~!")
            Text(text = "Thanks for goding through the LayoutStudy")
        }
    }

    @Composable
    fun BodyContent(modifier: Modifier = Modifier) {
        Column(modifier = modifier.padding(8.dp)) {
            Text(text = "Hi there~!")
            Text(text = "Thanks for goding through the LayoutStudy")
        }
    }




}