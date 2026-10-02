package com.aeroscape.wallpapers.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * A frosted-glass container: translucent white fill, a soft light border,
 * and rounded corners. This is the base surface used throughout the app
 * for anything that should feel like it's sitting on top of a wallpaper.
 */
@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    cornerRadius: androidx.compose.ui.unit.Dp = 28.dp,
    fillAlpha: Float = 0.22f,
    content: @Composable () -> Unit
) {
    val shape = RoundedCornerShape(cornerRadius)
    Box(
        modifier = modifier
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        Color.White.copy(alpha = fillAlpha + 0.06f),
                        Color.White.copy(alpha = fillAlpha)
                    )
                ),
                shape = shape
            )
            .border(1.dp, Color.White.copy(alpha = 0.35f), shape)
    ) {
        content()
    }
}
