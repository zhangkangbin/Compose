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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import com.baseui.MyBaseUi
import com.compose.api.BaseApi
import com.compose.api.bean.AticleListBean
import com.compose.http.HttpTool
import com.compose.ui.ComposeNavigation
import com.compose.ui.ComposeState
import com.compose.ui.atest.TestUi
import com.compose.ui.hilt.HitScreenMain
import com.compose.ui.hilt.Println
import com.compose.ui.hilt.SingletonViewModel
import com.compose.ui.list.ListViewScreen
import com.compose.ui.login.LoginScreen
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
                    Navigator(LoginScreen())

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
fun Greeting(name: String) {


   Column {
       Image(
           painter = painterResource(R.mipmap.bao),
           contentDescription = "Contact profile picture",
           modifier= Modifier
               .height(100.dp)
               .width(100.dp)
               .padding(15.dp)
       )


       Text(text = "Helld  oA $name!", modifier = Modifier
           .padding(15.dp)
           .clickable {

               Log.d("mytest", "my is log..");

               getHttpData()
           })
   }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeTheme {
        Greeting("Android AQQQAAAA")
    }
}

fun log(text:String){
    Log.d("mytest", text)

}

fun getHttpData(){

    HttpTool.getIntance.create(BaseApi::class.java).articleList().enqueue(object :Callback<AticleListBean>{
        override fun onResponse(call: Call<AticleListBean>, response: Response<AticleListBean>) {
            response.body()?.errorMsg?.let { Log.d("mytest", it) };
            val  data=response.body()?.takeIf {
                it.errorCode==0
            }?.data?.datas;

            data?.forEach {

                log(it.title)
            }

           // MessageList(data);

        }

        override fun onFailure(call: Call<AticleListBean>, t: Throwable) {

            Log.d("mytest", t.message.toString())

        }

    });
}



