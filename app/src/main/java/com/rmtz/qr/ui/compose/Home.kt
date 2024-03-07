package com.rmtz.qr.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rmtz.qr.ui.component.QrCodeView
import com.rmtz.qr.ui.component.innerPadding
import com.rmtz.qr.ui.theme.QRTheme

@Composable
fun Home(paddingValues: PaddingValues, navController: NavHostController? ) {

    Column {
        Spacer(modifier = Modifier
            .height(20.dp)
            .fillMaxWidth())
        QrCodeView(
            data = "https://github.com/lightsparkdev/compose-qr-code",
            modifier = Modifier.size(300.dp)
        )


    }
}

@Composable
@Preview(
    device = Devices.PIXEL_4,
    showSystemUi = true
)
fun HomePreview() {
    QRTheme {
        Home(innerPadding, null)
    }
}