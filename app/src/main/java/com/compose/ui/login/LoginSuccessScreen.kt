package com.compose.ui.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.core.screen.Screen
import com.compose.uitls.SharedPreferenceHelper
import javax.inject.Inject

/**
 * 接受一个参数，简单暴力
 */
class LoginSuccessScreen(private val userName:String) : Screen {

   // @Inject
//    lateinit var preference: SharedPreferenceHelper

    @Composable
    override fun Content() {
      //  val name=preference.getString("preference")
        Box(Modifier.fillMaxSize(),contentAlignment= Alignment.Center) {
            Text(text = "$userName login success!:")
        }

    }
}

class LoginSuccessScreenVM:ViewModel(){



    fun handleData(){



    }

}