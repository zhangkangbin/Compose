package com.compose.ui.login

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen


class LoginScreen() : Screen {


    @Preview(showBackground = true)
    @Composable
    override fun Content() {
    /*    val logViewModel = viewModel<LoginScreenViewModel>()
        // val logViewModel = LoginScreenViewModel() 错误写法
        // val logViewModel: LoginScreenViewModel = viewModel()**/
        LoginScreenMainUi2Test()
    }


    @Composable
    fun LoginScreenMainUi2Test(){

        val dataInfo by remember {
            mutableStateOf(Data())
        }
        val getDataType={
            dataInfo
        }

        val onDataChange=fun (dataType:DataType,data:String){
            if(data.isNotBlank()){
                dataInfo.loginTipsText=""
            }
            when(dataType){

                is DataType.UserName->  dataInfo.userName=data
                is DataType.Password-> dataInfo.password=data
            }


        }
        val onClickType=fun (clickType:ClickType){

            "user name  ${dataInfo.userName}".log()

            if(dataInfo.userName.isEmpty()){
                dataInfo.loginTipsText="user name is empty"
                "user name is empty".log()
                return
            }
            if(dataInfo.password.isEmpty()){
                dataInfo.loginTipsText="user password is empty"
                "user password is empty".log()
                return
            }
            dataInfo.loginTipsText=""
        }
        LoginScreenMainUi2(getDataType,onDataChange,onClickType)
    }

    /**
     * 抽出所有的状态。无状态组件。
     */
    @Composable
    fun LoginScreenMainUi2(
        getDataType: () -> Data,
        onDataChange: (DataType,String) -> Unit,
        onClickType: (ClickType) -> Unit
    ) {

        Log.d("mytest", "----------LoginScreenMainUi --Composable-------")

        Box(
            Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Log.d("mytest", "----------LoginScreenMainUi --Column-------")
                TextField(
                    value = getDataType().userName,
                    onValueChange = {
                        onDataChange(DataType.UserName,it)
                    })
                TextField(keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password, imeAction = ImeAction.Next
                ),
                    value = getDataType().password,
                    visualTransformation = PasswordVisualTransformation(),
                    onValueChange = {

                        onDataChange(DataType.Password,it)
                      //  passwordChange(it)
                    })

                Text(text = getDataType().loginTipsText)
                Button(modifier = Modifier.padding(top = 15.dp), onClick = {
                    onClickType(ClickType.Login)
                }, enabled = true) {
                    Text(text = "Login")
                }

            }


        }

    }
}