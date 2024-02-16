package com.rmtz.qr.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.rmtz.qr.ui.theme.QRTheme

@Composable
fun Home() {

}

@Composable
@Preview(
    device = Devices.PIXEL,
    showSystemUi = true
)
fun HomePreview() {
    QRTheme {
        Home()
    }
}