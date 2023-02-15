package com.compose.ui.home

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.R

@Composable
fun homeListCard(modifier: Modifier=Modifier){

   Row(modifier=modifier.padding(10.dp).background(color = MaterialTheme.colors.error).height(100.dp).clickable {  }) {

       Surface(
           modifier=Modifier.size(50.dp),
           shape = CircleShape,
           color = MaterialTheme.colors.onBackground
       ) {

           Image(painter = painterResource(id = R.mipmap.bao), contentDescription = "头像")
       }

       Column(Modifier.padding(10.dp).height(50.dp)) {

           Text(text = "Toylor", fontSize = 16.sp, fontWeight = FontWeight.Bold)
           Text(text = "3 minutes ago", fontSize = 14.sp)


       }
   }



}