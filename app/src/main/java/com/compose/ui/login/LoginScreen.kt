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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.launch


class LoginScreen() : Screen {

    //  @Inject
    // lateinit var preference: SharedPreferenceHelper

    @Preview(
        showBackground = true, showSystemUi = true
    )
    @Composable
    fun LoginScreenMainUiPreView() {

        var tips by remember {
            mutableStateOf("")
        }
        LoginScreenMainUi(userName = tips,
            password = "kk",
            loginTipsText = "kk",
            isShow = true,
            tips = "tips",
            userNameChange = {
                12

                tips = it

            },
            passwordChange = {

            },
            login = {

            })
    }

    /**
     * 抽出所有的状态。无状态组件。
     */
    @Composable
    fun LoginScreenMainUi(
        userName: String,
        password: String,
        loginTipsText: String,
        isShow: Boolean,
        tips: String,
        userNameChange: (String) -> Unit,
        passwordChange: (String) -> Unit,
        login: () -> Unit
    ) {

        Log.d("mytest", "----------LoginScreenMainUi --Composable-------")

        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Log.d("mytest", "----------LoginScreenMainUi --Column-------")
                TextField(value = userName, onValueChange = {
                    userNameChange(it)
                })
                TextField(keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password, imeAction = ImeAction.Next
                ),
                    value = password,
                    visualTransformation = PasswordVisualTransformation(),
                    onValueChange = {

                        passwordChange(it)
                    })

                Text(text = tips)
                Button(modifier = Modifier.padding(top = 15.dp), onClick = {
                    login()
                }, enabled = true) {
                    Text(text = loginTipsText)
                }

            }


        }

    }



    @Preview
    @Composable
    override fun Content() {
    /*    val logViewModel = viewModel<LoginScreenViewModel>()
        // val logViewModel = LoginScreenViewModel() 错误写法
        // val logViewModel: LoginScreenViewModel = viewModel()
        // 负责显示
        val state by logViewModel.loginState.collectAsState()
        val navigator = LocalNavigator.currentOrThrow


        logViewModel.loading = false
        var tips by rememberSaveable(Unit) { mutableStateOf("tips") }


        val scope = rememberCoroutineScope()
        var userName by remember {
            mutableStateOf("user name")
        }
        var password by remember {
            mutableStateOf("")
        }

        var loginTipsText by remember {
            mutableStateOf("Login")
        }


        LoginScreenMainUi(userName, password, loginTipsText, state, tips, {
            userName = it;
        }, {
            password = it;
        }) {

            Log.d("mytest", "LoginScreenMainUi button")
            scope.launch {
                // loginTipsText = "Login...."
                logViewModel.login(userName, password)
                //loginTipsText = "Login"
            }
        }*/

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

        val dataChange=fun (dataType:DataType,data:String){
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
        LoginScreenMainUi2(getDataType,dataChange,onClickType)
    }

    /**
     * 抽出所有的状态。无状态组件。
     */
    @Composable
    fun LoginScreenMainUi2(
        getDataType: () -> Data,
        dataChange: (DataType,String) -> Unit,
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
                        dataChange(DataType.UserName,it)
                    })
                TextField(keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password, imeAction = ImeAction.Next
                ),
                    value = getDataType().password,
                    visualTransformation = PasswordVisualTransformation(),
                    onValueChange = {

                        dataChange(DataType.Password,it)
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

  class Data{
      var userName by mutableStateOf("")
      var password by mutableStateOf("")
      var loginTipsText by mutableStateOf("")

  }
interface ClickType{

    object Login :ClickType
    object ForgetPassword :ClickType

}
interface DataChange{

       // get() = mutableStateOf("")

    object Login :ClickType
    object ForgetPassword :ClickType

}
interface DataType{

    object UserName :DataType
    object Password :DataType
    object LoginTips :DataType
}

fun String.log(){

    Log.d("mytest",this)
}