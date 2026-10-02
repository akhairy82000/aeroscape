package com.aeroscape.wallpapers.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.asAndroidCanvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.dp
import com.aeroscape.wallpapers.data.WallpaperSpec
import com.aeroscape.wallpapers.render.WallpaperRenderer

/**
 * A grid card that live-renders the wallpaper recipe at whatever size the
 * card ends up being -- there is no bitmap asset behind this, it's drawn
 * fresh every time via WallpaperRenderer.
 */
@Composable
fun WallpaperThumbnail(
    spec: WallpaperSpec,
    isFavorite: Boolean,
    onClick: () -> Unit,
    onToggleFavorite: () -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(20.dp)

    Box(
        modifier = modifier
            .aspectRatio(9f / 16f)
            .clip(shape)
            .border(1.dp, Color.White.copy(alpha = 0.4f), shape)
            .background(Color.Black.copy(alpha = 0.05f))
            .clickable { onClick() }
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawIntoCanvas { canvas ->
                WallpaperRenderer.draw(
    canvas.asAndroidCanvas(),
    size.width.toInt(),
    size.height.toInt(),
    spec
)

            }
        }

        // Favorite toggle, top-right corner
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
                .size(30.dp)
                .clip(CircleShape)
                .background(Color.Black.copy(alpha = 0.28f))
                .clickable { onToggleFavorite() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                contentDescription = if (isFavorite) "Remove from favorites" else "Add to favorites",
                tint = if (isFavorite) Color(0xFFFF6B9D) else Color.White,
                modifier = Modifier.size(16.dp)
            )
        }

        // Name label, bottom-left, over a soft scrim for legibility
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxSizeBottomScrim()
        )

        androidx.compose.material3.Text(
            text = spec.name,
            color = Color.White,
            style = androidx.compose.material3.MaterialTheme.typography.labelMedium,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(10.dp)
        )
    }
}

private fun Modifier.fillMaxSizeBottomScrim(): Modifier = this
    .fillMaxSize()
    .drawWithContent {
        drawContent()
        drawRect(
            brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.45f)),
                startY = size.height * 0.55f,
                endY = size.height
            )
        )
    }
