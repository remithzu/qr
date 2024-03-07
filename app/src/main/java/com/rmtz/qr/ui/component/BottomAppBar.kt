package com.rmtz.qr.ui.component

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.guru.fontawesomecomposelib.FaIcon
import com.guru.fontawesomecomposelib.FaIcons
import com.rmtz.qr.navigation.Screen

@Composable
fun BottomAppBar(navController: NavHostController) {
    val selectedIndex = remember { mutableStateOf(0) }
    BottomNavigation(elevation = 10.dp) {
        BottomNavigationItem(icon = {
            FaIcon(
                faIcon = FaIcons.Home,
                tint = if (selectedIndex.value == 0){
                    Color.White
                } else {
                    Color.DarkGray
                })
        },
            label = { Text(text = "Home") },
            selected = (selectedIndex.value == 0),
            onClick = {
                selectedIndex.value = 0
                navController.navigate(Screen.Home.route)
            })

        BottomNavigationItem(icon = {
            FaIcon(
                faIcon = FaIcons.History,
                tint = if (selectedIndex.value == 1){
                    Color.White
                } else {
                    Color.DarkGray
                }
            )
        },
            label = { Text(text = "History") },
            selected = (selectedIndex.value == 1),
            onClick = {
                selectedIndex.value = 1
                navController.navigate(Screen.History.route)
            })

        BottomNavigationItem(icon = {
            FaIcon(
                faIcon = FaIcons.Qrcode,
                tint = if (selectedIndex.value == 2){
                    Color.White
                } else {
                    Color.DarkGray
                }
            )
        },
            label = { Text(text = "Generate") },
            selected = (selectedIndex.value == 2),
            onClick = {
                selectedIndex.value = 2
                navController.navigate(Screen.Generate.route)
            })
    }
}