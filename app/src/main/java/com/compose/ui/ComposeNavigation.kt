package com.compose.ui

import android.util.Log
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

/**
 * 导航
 */
class ComposeNavigation {

    @Preview
    @Composable
    fun ComposeNavigationScreen(){



        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = RouteConfig.Screen_A) {
            composable(RouteConfig.Screen_A) {
                ScreenA {
                    navController.navigate("${RouteConfig.Screen_B}/Zkang")
                }
            }
            composable("${RouteConfig.Screen_B}/{${ParamsConfig.NAME}}") { it ->



                it.arguments?.getString(ParamsConfig.NAME)?.let { it1 -> Log.d("mytest", it1) }


                ScreenB{
                    navController.navigate(RouteConfig.Screen_C)
                   // navController.popBackStack()
                }
            }
            composable(RouteConfig.Screen_C) {
                ScreenC {
                    navController.navigate(RouteConfig.Screen_A)
                    //navController.popBackStack()
                }
            }
        }


    }

     @Composable
    fun ScreenC(onClick:()->Unit) {

        Button(onClick =onClick) {
            Text(text = "CCC")
        }
    }

     @Composable
    fun ScreenB(onClick:()->Unit) {
         Button(onClick =onClick) {

             Text(text = "BBBB")
         }  }

    @Composable
    fun ScreenA(onClick:()->Unit) {

        Button(onClick =onClick) {

            Text(text = "AAAA")
        }
    }

}

/**
 * 方便管理路由
 */
object RouteConfig{

    const val Screen_A="A"
    const val Screen_B="B"
    const val Screen_C="C"
}

object ParamsConfig{

    const val NAME="name"

}

