package com.compose.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
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

        Column {
            SearchBar()
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                BodyElement()
                BodyElement()
                BodyElement()
                BodyElement()
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

            CardListItem()
            CardListItem()
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

    @Preview
    @Composable
    fun CardListItem() {

        Surface(shape = MaterialTheme.shapes.small, modifier = Modifier.padding(6.dp)) {
            Row(
                Modifier
                    .width(192.dp)
                    .height(56.dp)) {

                Image(
                    painter = painterResource(id = R.drawable.img),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(56.dp)
                )

                Text(text = "Nature..", Modifier.padding(6.dp))
            }
        }
    }
}