package com.rmtz.qr.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.guru.fontawesomecomposelib.FaIcon
import com.guru.fontawesomecomposelib.FaIcons
import com.rmtz.qr.navigation.Screen

@Composable
fun ExtendedFloatingActionButton(navController: NavHostController) {
    androidx.compose.material3.ExtendedFloatingActionButton(
        icon = { FaIcon(faIcon = FaIcons.Camera) },
        text = { Text("Scan") },
        onClick = { navController.navigate(Screen.Scanner.route) },
        elevation = FloatingActionButtonDefaults.elevation(8.dp)
    )
}