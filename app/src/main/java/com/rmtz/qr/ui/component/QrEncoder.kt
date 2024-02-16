package com.rmtz.qr.ui.component

import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import com.google.zxing.qrcode.encoder.ByteMatrix
import com.google.zxing.qrcode.encoder.Encoder

interface QrEncoder {
    fun encode(qrData: String): ByteMatrix?
}

class ZxingQrEncoder : QrEncoder {
    override fun encode(qrData: String): ByteMatrix? {
        return Encoder.encode(
            qrData,
            ErrorCorrectionLevel.H,
            mapOf(
                EncodeHintType.CHARACTER_SET to "UTF-8",
                EncodeHintType.MARGIN to 16,
                EncodeHintType.ERROR_CORRECTION to ErrorCorrectionLevel.H,
            )
        ).matrix
    }
}