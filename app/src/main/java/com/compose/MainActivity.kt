package com.compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*


import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import cafe.adriel.voyager.navigator.Navigator
import com.baseui.MyBaseUi
import com.compose.api.BaseApi
import com.compose.api.bean.AticleListBean
import com.compose.http.HttpTool
import com.compose.ui.ComposeNavigation
import com.compose.ui.ComposeState
import com.compose.ui.atest.TestUi
import com.compose.ui.flow.FlowTestScreen
import com.compose.ui.hilt.HitScreenMain
import com.compose.ui.hilt.Println
import com.compose.ui.hilt.SingletonViewModel
import com.compose.ui.list.ListViewScreen
import com.compose.ui.login.LoginScreen
import com.compose.ui.login.log
import com.compose.ui.navigation.PostListScreen
import com.compose.ui.theme.ComposeTheme
import com.compose.ui.theme.Purple700
import com.compose.uitls.SharedPreferenceHelper
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var print: Println


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            ComposeTheme {

                // A surface container using the 'background' color from the theme
                Surface(color = Purple700) {
                  //  listView(getData())

                    //homeListCard();
                    //ComposeState().ListMainView()
                   // ComposeNavigation().ComposeNavigationScreen()
                    //PostListScreen().Content()
                   // Navigator(LoginScreen())
                    "---onCreate---".log()
                    DialogUi()
                   // ListViewScreen().ListViewScreenUi()
                    //MyBaseUi().MyDialog()
                  //  TestUi().Foo()
                    //Navigator(LoginScreen())

                    //HitScreenMain().testSingle()
/*
                    print.print("----------main-------")
                    val singleton: SingletonViewModel by viewModels()
                    //val singleton= SingletonViewModel()
                    singleton.postData()*/
                }
            }
        }

    }


}


@Composable
fun rememberDialog(): DialogScreen {

    "---rememberDialog---".log()
   val dialog by remember {
       mutableStateOf(DialogScreen())
   }

    if(dialog.isShow){
        Dialog(onDismissRequest = {
            dialog.dismiss()

        }) {

            Button(onClick = {
                dialog.dismiss()
            }) {
                Text(text = "dismiss me")
            }



        }

    }

   return dialog

}

@Preview
@Composable
fun DialogUi() {


    Box {
        "---DialogUi--Box-".log()
        val dialog = rememberDialog()

        Button(onClick = {
            dialog.show()
        }) {
            Text(text = "show me the dialog")
        }
    }

    "---DialogUi---".log()

}

class DialogScreen{

    var isShow by mutableStateOf(false)

    fun show(){

        isShow=true
    }

    fun dismiss(){

        isShow=false
    }

}




fun log(text:String){
    Log.d("mytest", text)

}




