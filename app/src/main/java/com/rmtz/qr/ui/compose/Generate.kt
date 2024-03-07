package com.rmtz.qr.ui.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.rmtz.qr.ui.component.innerPadding
import com.rmtz.qr.ui.theme.QRTheme

@Composable
fun Generate(paddingValues: PaddingValues, navController: NavHostController? ) {

}

@Composable
@Preview(
    device = Devices.PIXEL_4,
    showSystemUi = true
)
fun GeneratePreview() {
    QRTheme {
        Generate(innerPadding, null)
    }
}