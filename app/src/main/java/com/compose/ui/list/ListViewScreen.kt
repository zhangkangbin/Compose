package com.compose.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.material.placeholder

class ListViewScreen : BaseListViewScreen<UserInfo>() {


    @Composable
    override fun GetItemView(dataInfo: UserInfo) {

        Text(text =dataInfo.userName, modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray)
            .placeholder(visible = true)
            .padding(20.dp) )
    }

    override fun addInfo(id: Int, value: String): UserInfo {
       val info=UserInfo(id,value)
        info.userName="$id addInfo"
        return info
    }

    override fun getUrl(): String {
       return "http://test"
    }
}

class UserInfo(id: Int, value: String) : DataInfo(id, value) {

    var userName=""

}