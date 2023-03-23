package com.compose.ui.img

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.R

class ImageScreen {

    @Preview
    @Composable
    fun ImageScreenUi(){

        Column(Modifier.fillMaxSize().background(Color.Cyan),
            verticalArrangement= Arrangement.Center,
            horizontalAlignment= Alignment.CenterHorizontally,
        ) {
            Image(painter = painterResource(id = R.mipmap.img_loading_9),
                contentDescription =null,
                contentScale= ContentScale.Inside,
                modifier = Modifier.width(300.dp).height(300.dp).background(Color.Blue)
            )
            Text(text = "精品宣传")
        }
    }
}