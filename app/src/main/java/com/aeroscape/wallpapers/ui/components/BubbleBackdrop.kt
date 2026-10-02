package com.aeroscape.wallpapers.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import kotlin.math.PI
import kotlin.math.sin
import kotlin.random.Random

private data class Bubble(
    val xFraction: Float,
    val radius: Float,
    val phase: Float,
    val swayAmplitude: Float
)

/**
 * A handful of soft, slowly rising translucent circles drifting up behind
 * the content -- a light decorative touch that keeps the glass UI feeling
 * alive without being distracting.
 */
@Composable
fun BubbleBackdrop(modifier: Modifier = Modifier, bubbleColor: Color = Color.White, count: Int = 7) {
    val bubbles = remember {
        List(count) {
            Bubble(
                xFraction = Random.nextFloat(),
                radius = Random.nextFloat() * 36f + 14f,
                phase = Random.nextFloat(),
                swayAmplitude = Random.nextFloat() * 24f + 6f
            )
        }
    }

    val infiniteTransition = rememberInfiniteTransition(label = "bubbleTime")
    val time by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 22000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "time"
    )

    Canvas(modifier = modifier.fillMaxSize()) {
        bubbles.forEach { bubble ->
            val progress = (time + bubble.phase) % 1f
            val y = size.height * (1f - progress)
            val sway = sin(progress * 2f * PI.toFloat()) * bubble.swayAmplitude
            val x = size.width * bubble.xFraction + sway
            val alpha = sin(progress * PI.toFloat()).coerceIn(0f, 1f) * 0.30f

            drawCircle(
                color = bubbleColor.copy(alpha = alpha),
                radius = bubble.radius,
                center = Offset(x, y)
            )
        }
    }
}
