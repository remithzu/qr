package com.rmtz.qr.navigation

import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rmtz.qr.ui.compose.Home
import timber.log.Timber
import android.os.Process
import com.rmtz.qr.ui.compose.Generate
import com.rmtz.qr.ui.compose.History
import com.rmtz.qr.ui.compose.Scanner
import com.rmtz.qr.ui.theme.Animations.SlideIn
import com.rmtz.qr.ui.theme.Animations.SlideOut

@Composable
fun Navigation() {
    val navController: NavHostController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavController(navController, currentRoute)
}

@Composable
fun NavController(navController: NavHostController, currentRoute: String?) {
    Timber.d("route $currentRoute")
    val context = LocalContext.current
    var lastBackPressedTime by remember { mutableLongStateOf(0L) }
    val dispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    val callback = rememberUpdatedState(newValue = {
        if (currentRoute != "Dashboard") {
            if (System.currentTimeMillis() - lastBackPressedTime < 1000) {
                navController.navigate("Dashboard") {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            } else {
                Toast.makeText(context, "Press back again to exit", Toast.LENGTH_SHORT).show()
            }
            lastBackPressedTime = System.currentTimeMillis()
        } else {
            if (System.currentTimeMillis() - lastBackPressedTime < 1000) {
                context.let {
                    try {
                        (it as? OnBackPressedDispatcherOwner)?.onBackPressedDispatcher?.onBackPressed()
                    } catch (e:Exception) {
                        handleExit()
                    }
                }
            } else {
                Toast.makeText(context, "Press back again to exit", Toast.LENGTH_SHORT).show()
            }
            lastBackPressedTime = System.currentTimeMillis()
        }
    })

    DisposableEffect(dispatcher) {
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                try {
                    callback.value()
                } catch (e: Exception) {
                    handleExit()
                }
            }
        }
        dispatcher?.addCallback(onBackPressedCallback)

        onDispose {
            onBackPressedCallback.remove()
        }
    }

    NavHost(
        navController = navController,
        route = Graph.MAIN,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route,
            enterTransition = { SlideIn },
            exitTransition = { SlideOut },
            content = {
                Home(navController)
            }
        )
        composable(
            route = Screen.History.route,
            enterTransition = { SlideIn },
            exitTransition = { SlideOut },
            content = {
                History(navController)
            }
        )
        composable(
            route = Screen.Generate.route,
            enterTransition = { SlideIn },
            exitTransition = { SlideOut },
            content = {
                Generate(navController)
            }
        )
        composable(
            route = Screen.Scanner.route,
            enterTransition = { SlideIn },
            exitTransition = { SlideOut },
            content = {
                Scanner(navController)
            }
        )
    }
}

fun handleExit() {
    Process.killProcess(Process.myPid())
}
