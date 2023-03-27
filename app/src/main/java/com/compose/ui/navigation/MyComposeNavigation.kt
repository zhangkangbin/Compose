package com.compose.ui.navigation

import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

@Navigator.Name("composable")
class MyComposeNavigation : Navigator<MyComposeNavigation.Destination>() {

    override fun navigate(
        destination: Destination,
        args: Bundle?,
        navOptions: NavOptions?,
        navigatorExtras: Extras?
    ): NavDestination? {
        val router=args?.getString("")

        Log.d("mytest","-------------router:$router")
        return super.navigate(destination, args, navOptions, navigatorExtras)
    }
    override fun createDestination(): Destination {

        return Destination(this) { }
    }
    @NavDestination.ClassType(Composable::class)
    public class Destination(
        navigator: MyComposeNavigation,
        internal val content: @Composable (NavBackStackEntry) -> Unit
    ) :NavDestination(navigator)
}