package com.rmtz.qr.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("Home")
    data object History : Screen("History")
    data object Generate : Screen("Generate")
    data object Scanner : Screen("Scanner")
}