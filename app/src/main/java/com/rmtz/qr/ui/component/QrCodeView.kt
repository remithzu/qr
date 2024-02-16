package com.rmtz.qr.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rmtz.qr.data.QrCodeColors

@Composable
fun QrCodeView(
    data: String,
    modifier: Modifier = Modifier,
    colors: QrCodeColors = QrCodeColors.default(),
    dotShape: DotShape = DotShape.Square,
    encoder: QrEncoder = ZxingQrEncoder(),
    overlayContent: (@Composable () -> Unit)? = null,
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        QrCodeView(
            data,
            modifier = Modifier.fillMaxSize(),
            colors = colors,
            dotShape = dotShape,
            encoder = encoder
        )
        if (overlayContent != null) {
            Box(modifier = Modifier.fillMaxSize(fraction = 0.25f)) {
                overlayContent()
            }
        }
    }
}

@Composable
fun QrCodeView(
    data: String,
    modifier: Modifier = Modifier,
    colors: QrCodeColors = QrCodeColors.default(),
    dotShape: DotShape = DotShape.Square,
    encoder: QrEncoder = ZxingQrEncoder()
) {
    val encodedData = remember(data, encoder) { encoder.encode(data) }

    Canvas(modifier = modifier.background(colors.background)) {
        encodedData?.let { matrix ->
            val cellSize = size.width / matrix.width
            for (x in 0 until matrix.width) {
                for (y in 0 until matrix.height) {
                    if (matrix.get(x, y) != 1.toByte() || isFinderCell(x, y, matrix.width)) continue
                    when (dotShape) {
                        DotShape.Square -> drawRect(
                            color = colors.foreground,
                            topLeft = Offset(x * cellSize, y * cellSize),
                            size = Size(cellSize, cellSize)
                        )
                        DotShape.Circle -> drawCircle(
                            color = colors.foreground,
                            center = Offset(
                                x * cellSize + cellSize / 2, y * cellSize + cellSize / 2
                            ),
                            radius = cellSize / 2
                        )
                    }
                }
            }
            drawFinderSquares(cellSize, colors, dotShape)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QrCodeViewPreview() {
    QrCodeView(
        "https://lightspark.com/this-is-a-test-of-longer-urls-to-see-how-it-looks",
        modifier = Modifier.size(400.dp),
        colors = QrCodeColors(
            background = Color.White,
            foreground = Color.Black
        ),
        dotShape = DotShape.Circle,
        overlayContent = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(8.dp)
                    .clip(CircleShape)
                    .background(Color.Yellow)
            ) {
                Canvas(modifier = Modifier.fillMaxSize(fraction = 0.5f)) {
                    // Draw a smiley face
                    drawCircle(
                        color = Color.Black,
                        center = Offset(size.width / 2, size.height / 2),
                        radius = size.width / 2
                    )
                    drawCircle(
                        color = Color.White,
                        center = Offset(
                            size.width / 2 - size.width / 4,
                            size.height / 2 - size.height / 4
                        ),
                        radius = size.width / 8
                    )
                    drawCircle(
                        color = Color.White,
                        center = Offset(
                            size.width / 2 + size.width / 4,
                            size.height / 2 - size.height / 4
                        ),
                        radius = size.width / 8
                    )
                    drawArc(
                        color = Color.White,
                        startAngle = 0f,
                        sweepAngle = 180f,
                        useCenter = false,
                        topLeft = Offset(size.width / 4, size.height / 3),
                        size = Size(size.width / 2, size.height / 2),
                        style = Stroke(width = size.width / 8)
                    )
                }
            }
        })
}