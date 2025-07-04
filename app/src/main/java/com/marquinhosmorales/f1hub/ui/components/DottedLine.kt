package com.marquinhosmorales.f1hub.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.unit.Dp

enum class LineOrientation {
    Vertical,
    Horizontal
}

@Composable
fun DottedLine(
    color: Color,
    orientation: LineOrientation,
    modifier: Modifier = Modifier,
    width: Dp,
    height: Dp
) {
    Canvas(
        modifier = modifier
            .width(width)
            .height(height)
    ) {
        val (start, end, strokeWidth) = when (orientation) {
            LineOrientation.Vertical -> {
                Triple(
                    Offset(x = size.width / 2, y = 0f),
                    Offset(x = size.width / 2, y = size.height),
                    width.toPx() // Use width as strokeWidth for vertical line
                )
            }
            LineOrientation.Horizontal -> {
                Triple(
                    Offset(x = 0f, y = size.height / 2),
                    Offset(x = size.width, y = size.height / 2),
                    height.toPx() // Use height as strokeWidth for horizontal line
                )
            }
        }
        drawLine(
            color = color,
            start = start,
            end = end,
            strokeWidth = strokeWidth,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(5f, 5f), phase = 0f)
        )
    }
}