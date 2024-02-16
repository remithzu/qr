package com.rmtz.qr.data

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

data class QrCodeColors(
    val background: Color, val foreground: Color
) {
    companion object {
        @Composable
        fun default() = QrCodeColors(
            background = Color.White,
            foreground = Color.Black
        )
    }
}