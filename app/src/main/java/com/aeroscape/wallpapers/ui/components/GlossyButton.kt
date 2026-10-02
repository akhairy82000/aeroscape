package com.aeroscape.wallpapers.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aeroscape.wallpapers.ui.theme.SkyBlueDeep
import com.aeroscape.wallpapers.ui.theme.SkyBlueMid

/**
 * A pill-shaped button with a vertical gradient fill and a subtle top-half
 * gloss highlight -- the classic Aero "wet look" button.
 */
@Composable
fun GlossyButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    fillMaxWidth: Boolean = false,
    gradientColors: List<Color> = listOf(SkyBlueMid, SkyBlueDeep),
    leadingIcon: (@Composable () -> Unit)? = null
) {
    val shape = RoundedCornerShape(50)
    val widthModifier = if (fillMaxWidth) Modifier.fillMaxWidth() else Modifier

    Box(
        modifier = modifier
            .then(widthModifier)
            .clip(shape)
            .background(Brush.verticalGradient(gradientColors.map { it.copy(alpha = if (enabled) 1f else 0.5f) }))
            .clickable(enabled = enabled) { onClick() }
    ) {
        // Gloss highlight across the top half. The parent Box is already
        // clipped to the pill shape, so this plain rectangle gets clipped
        // to the same outline automatically -- no extra shape math needed.
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
                .align(Alignment.TopStart)
                .background(
                    Brush.verticalGradient(
                        listOf(Color.White.copy(alpha = 0.35f), Color.Transparent)
                    )
                )
        )

        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(PaddingValues(horizontal = 24.dp, vertical = 14.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (leadingIcon != null) {
                leadingIcon()
            }
            Text(
                text = text,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
