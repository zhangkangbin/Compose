package com.compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.api.BaseApi
import com.compose.api.bean.AticleListBean
import com.compose.http.HttpTool
import com.compose.ui.theme.ComposeTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                }
            }
        }




        HttpTool.getIntance.create(BaseApi::class.java).articleList().enqueue(object :Callback<AticleListBean>{
            override fun onResponse(call: Call<AticleListBean>, response: Response<AticleListBean>) {
                response.body()?.errorMsg?.let { Log.d("mytest", it) };
                response.body()?.errorCode?.let { Log.d("mytest", it.toString()) };
            }

            override fun onFailure(call: Call<AticleListBean>, t: Throwable) {
                // TODO("Not yet implemented")
            }

        });


    }
}
/*fun   downLoad(){
    val listener: DownloadListener = object : DownloadListener() {

        override fun onDownloadSuccess(item: VideoTaskItem?) {
            super.onDownloadSuccess(item)
        }
    }
    VideoDownloadManager.getInstance().setGlobalDownloadListener(listener);

}*/
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
fun getData(): MutableList<ListData> {

    val data = mutableListOf(ListData("Adam", 20));

    data.add(ListData("Adam1", 21));
    data.add(ListData("Adam1", 22));
    data.add(ListData("Adam3", 23));

    return data;

}
data class ListData(val name: String, var age: Int)
