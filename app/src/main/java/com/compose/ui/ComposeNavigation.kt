package com.compose.ui

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.compose.router.InterceptorViewModel

/**
 * 导航
 */
class ComposeNavigation {


    @Preview
    @Composable
    fun ComposeNavigationScreen() {


        val navigationViewModel= InterceptorViewModel()


        val navController = rememberNavController()




        //val navController  by remember { mutableStateOf(Overview) }
        NavHost(navController = navController, startDestination = RouteConfig.Screen_A) {
            composable(RouteConfig.Screen_A) {
                ScreenA {
                    Log.d("mytest", "-------------A screen-----------------");
                   //  navController.navigateSingleTopTo("${RouteConfig.Screen_B}/kang")
                 //   navController.navigateSingleTopTo(RouteConfig.Screen_B)
                    navigationViewModel.navigateSingleTopTo(navController,"${RouteConfig.Screen_B}/kang")

                }
            }

            composable("${RouteConfig.Screen_B}/{${ParamsConfig.NAME}}") { it ->
                ScreenB {
                    it.arguments?.getString(ParamsConfig.NAME)?.let { it1 -> Log.d("mytest", it1) }
                    Log.d("mytest", "-------------B screen-----------------");
                  //  navController.navigate(RouteConfig.Screen_C)
                    navigationViewModel.navigateSingleTopTo(navController,RouteConfig.Screen_C)

                    // navController.popBackStack()
                }

            }

           composable(RouteConfig.Screen_C) {
                ScreenC {
                    Log.d("mytest", "-------------C screen-----------------");
                  //  navController.navigate(RouteConfig.Screen_A)
                    navigationViewModel.navigateSingleTopTo(navController,RouteConfig.Screen_A)
                    //navController.popBackStack()
                }
            }

        }


    }


    @Composable
    fun ScreenA(onClick: () -> Unit) {

        Button(onClick = onClick, Modifier.fillMaxWidth()) {

            Text(text = "AAAA")
        }
    }



    @Composable
    fun ScreenB(onClick: () -> Unit) {

        Button(onClick = onClick, Modifier.fillMaxWidth()) {
            Text(text = "BBB")
        }
    }

    @Composable
    fun ScreenC(onClick: () -> Unit) {

        Button(onClick = onClick, Modifier.fillMaxWidth()) {
            Text(text = "CCC")
        }
    }
/*
    private fun NavHostController.navigateSingleTopTo(route: String) =
        this.navigate(route) { launchSingleTop = true }*/


}

/**
 * 方便管理路由
 */
object RouteConfig {

    const val Screen_A = "router://A"
    const val Screen_B = "router://B"
    const val Screen_C = "router://C"
}

object ParamsConfig {

    const val NAME = "name"

}

/**
 * interface RouteConfig {

val route: String
val screen: @Composable () -> Unit
}
/**
 * 方便管理路由
*/
object AScreen :RouteConfig{

const val Screen_A="A"
const val Screen_B="B"
const val Screen_C="C"
override val route = "A"
override val screen: @Composable () -> Unit = {
ComposeNavigation().ScreenA {

}
}
}
 */
