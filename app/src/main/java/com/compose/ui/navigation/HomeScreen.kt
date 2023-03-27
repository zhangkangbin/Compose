package com.compose.ui.navigation

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

/**
 * https://voyager.adriel.cafe/
 */
class PostListScreen : Screen {

    @Preview
    @Composable
    override fun Content() {
        // ...

        val navigator = LocalNavigator.currentOrThrow

        Button(onClick = {

            navigator.push(PostListScreenB())
        }) {


            Text(text = "AAAAAAA$key")
        }

    }
}

class PostListScreenB : Screen {

    @Composable
    override fun Content() {
        // ...

        val navigator = LocalNavigator.currentOrThrow

        Button(onClick = {

            navigator.push(PostListScreenC())
        }) {

            Text(text = "BBBBBBBB$key")
        }


    }
}

class PostListScreenC : Screen {

    @Composable
    override fun Content() {
        // ...

        val navigator = LocalNavigator.currentOrThrow

        Button(onClick = {

            navigator.push(PostListScreen())
        }) {

            Text(text = "CCCCCCC$key")
        }

    }
}