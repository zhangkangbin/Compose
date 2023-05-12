package com.compose.ui.navigation

import android.util.Log
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.stack.SnapshotStateStack
import cafe.adriel.voyager.core.stack.rememberStateStack
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import java.lang.reflect.Field

/**
 * https://voyager.adriel.cafe/
 */
class PostListScreen : Screen {


    @Preview
    @Composable
    override fun Content() {
        // ...
        val stateStack = rememberStateStack<Screen>()
        //val privateObject = PrivateObject("The Private Value")
        val navigator = LocalNavigator.currentOrThrow
        val privateStringField: Field = SnapshotStateStack::class.java.getDeclaredField("stateStack")

        privateStringField.isAccessible = true

        val list=privateStringField.get(stateStack) as SnapshotStateList<String>



        Button(onClick = {
            Log.d("mytest", "navigator size:${navigator.toString()}")
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

class TestField{

    private val name="kang"
}

class PostListScreenC : Screen {

    @Composable
    override fun Content() {
        // ...

        val stateStack = rememberStateStack<String>()
        val navigator = LocalNavigator.currentOrThrow
        val privateStringField: Field = TestField::class.java.getDeclaredField("name")

         privateStringField.isAccessible = true

        val data=TestField()

        val list=privateStringField.get(data)


        Button(onClick = {

           // val data=navigator as SnapshotStateStack<Screen>
            navigator.push(PostListScreen())
            Log.d("mytest", "navigator size:${navigator.size}")
            Log.d("mytest", "stateStack size:${stateStack.size}")
          //  Log.d("mytest", "list size:${list.size}")
        }) {

            Text(text = "CCCCCCC$key")
        }

    }
}