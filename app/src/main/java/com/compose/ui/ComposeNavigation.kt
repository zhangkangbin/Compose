package com.compose.ui

import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.compose.router.InterceptorViewModel
import com.compose.ui.navigation.MyComposeNavigation

/**
 * 导航
 */
class ComposeNavigation {

    @Preview
    @Composable
    fun ComposeNavigationScreen() {

        val navController = rememberNavController()
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            destination.route?.let {
                Log.d(
                    "mytest",
                    "----------addOnDestinationChangedListener-----$it"
                )
            }
        }
        val navigationViewModel = InterceptorViewModel(navController)

        //val request=NavDeepLinkRequest.Builder()

        //val navController  by remember { mutableStateOf(Overview) }
        NavHost(navController = navController, startDestination = RouteConfig.Screen_A) {
            composable(RouteConfig.Screen_A) {
                ScreenA {
                    Log.d("mytest", "-------------A screen-----------------");
                    //  navController.navigateSingleTopTo("${RouteConfig.Screen_B}/kang")
                    //   navController.navigateSingleTopTo(RouteConfig.Screen_B)
                    navigationViewModel.navigateSingleTopTo("${RouteConfig.Screen_B}/kang")

                }
            }

            composable("${RouteConfig.Screen_B}/{${ParamsConfig.NAME}}") { it ->
                ScreenB {
                    it.arguments?.getString(ParamsConfig.NAME)?.let { it1 -> Log.d("mytest", it1) }
                    Log.d("mytest", "-------------B screen-----------------");
                    navController.navigate(RouteConfig.Screen_C)
                  //  navigationViewModel.navigateSingleTopTo(RouteConfig.Screen_C)
                    //源码还是封装成NavDeepLinkRequest
                    // navController.popBackStack()
                }

            }

            composable(RouteConfig.Screen_C) {
                ScreenC {
                    Log.d("mytest", "-------------C screen-----------------");
                    //  navController.navigate(RouteConfig.Screen_A)
                    navigationViewModel.navigateSingleTopTo(RouteConfig.Screen_E)
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
    @Composable
    fun ScreenD(onClick: () -> Unit) {

        Button(onClick = onClick, Modifier.fillMaxWidth()) {
            Text(text = "ScreenD")
        }
    }


    @Composable
    fun ScreenE(onClick: () -> Unit) {

        Button(onClick = onClick, Modifier.fillMaxWidth()) {
            Text(text = "ScreenD")
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
    const val Screen_D = "router://D"
    const val Screen_E = "router://E"
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
