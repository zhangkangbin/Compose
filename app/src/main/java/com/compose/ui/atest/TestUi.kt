package com.compose.ui.atest

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class TestUi {

    @Preview
    @Composable
    fun test() {

        Column(Modifier.padding(20.dp)) {
            Surface(
                elevation = 2.dp,
                border = BorderStroke(2.dp, Color.Red),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.padding(20.dp)
            ) {

                Text(
                    text = "d",
                    Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .background(Color.Blue)
                )
            }

            /*          Box (
                          Modifier
                              .fillMaxSize()
                              .padding(20.dp)){
                          Column(modifier = Modifier
                              .shadow(
                                  elevation = 4.dp,
                                  clip = true,
                                  ambientColor = Color.White,
                                  spotColor = Color.White,
                                  shape = RoundedCornerShape(10.dp)
                              )
                              .width(100.dp)
                              .height(100.dp)

                          ) {

                              Text(text = "ABBBA")
                          }
                      }
          */
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .clickable { },
                elevation = 10.dp,
                shape = RoundedCornerShape(10.dp),
            ) {
                Column(
                    modifier = Modifier.padding(15.dp)
                ) {
                    Text(text = "AA")
                }
            }


            Column(
                modifier = Modifier
                    .shadow(
                        elevation = 4.dp,
                        clip = true,
                        /*       ambientColor= Color.White,
                        spotColor= Color.White,*/
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                    .border(
                        border = BorderStroke(2.dp, Color.White),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .width(100.dp)
                    .height(100.dp)


            ) {

                Text(text = "ABBBA",Modifier.padding(20.dp))
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier
                    .shadow(
                        elevation = 4.dp,
                        clip = true,
                        /*       ambientColor= Color.White,
                               spotColor= Color.White,*/
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                    //.border(border = BorderStroke(2.dp, Color.White),shape = RoundedCornerShape(10.dp))
                    .width(100.dp)
                    .height(100.dp)


            ) {

                Text(text = "ABBBA",Modifier.padding(20.dp))
            }

        }
    }

}