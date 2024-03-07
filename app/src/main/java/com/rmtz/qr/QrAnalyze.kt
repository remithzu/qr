package com.rmtz.qr

import android.graphics.ImageFormat
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import timber.log.Timber
import java.nio.ByteBuffer

class QrAnalyze(private val onQrCodeDetected: (String) -> Unit ) : ImageAnalysis.Analyzer {
    private val supportedImageFormats = listOf<Int>(
        ImageFormat.YUV_420_888,
        ImageFormat.YUV_422_888,
        ImageFormat.YUV_444_888
    )

    @OptIn(ExperimentalGetImage::class)
    override fun analyze(imageProxy: ImageProxy) {

        val options = BarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
            .build()

        val scanner = BarcodeScanning.getClient(options)
        val mediaImage = imageProxy.image
        mediaImage?.let {
            val inputImage = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

            scanner.process(inputImage)
                .addOnSuccessListener { barcodes ->
                    for (barcode in barcodes) {
                        val rawValue = barcode.rawValue
                        if (barcode.format == Barcode.FORMAT_QR_CODE && !rawValue.isNullOrBlank()) {
                            // QR code detected, pass data to callback
                            onQrCodeDetected(rawValue)
                        }
                    }
                    imageProxy.close()
                }
                .addOnFailureListener {
                    onQrCodeDetected("Err: ${it.message}")
                    imageProxy.close()
                }
        }
        imageProxy.close()
    }

    private fun ByteBuffer.toByArray(): ByteArray {
        rewind()
        return ByteArray(remaining()).also {
            get(it)
        }
    }

}