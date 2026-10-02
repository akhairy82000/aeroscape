package com.aeroscape.wallpapers.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aeroscape.wallpapers.render.WallpaperRenderer
import com.aeroscape.wallpapers.ui.components.GlassCard
import com.aeroscape.wallpapers.ui.components.GlossyButton
import com.aeroscape.wallpapers.ui.viewmodel.WallpaperViewModel
import com.aeroscape.wallpapers.util.MediaSaver
import com.aeroscape.wallpapers.util.WallpaperSetter
import com.aeroscape.wallpapers.util.WallpaperTarget
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun PreviewScreen(
    wallpaperId: String,
    viewModel: WallpaperViewModel,
    onBack: () -> Unit
) {
    val spec = viewModel.wallpaperById(wallpaperId) ?: return
    val favorites by viewModel.favorites.collectAsStateWithLifecycle()
    val isFavorite = spec.id in favorites

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var target by remember { mutableStateOf(WallpaperTarget.HOME) }
    var isApplying by remember { mutableStateOf(false) }
    var isSaving by remember { mutableStateOf(false) }
    var showSuccess by remember { mutableStateOf(false) }
    var statusMessage by remember { mutableStateOf<String?>(null) }

    Box(modifier = Modifier.fillMaxSize()) {

        // Full-bleed live preview of the wallpaper recipe
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawIntoCanvas { canvas ->
                WallpaperRenderer.draw(
                    canvas.nativeCanvas,
                    size.width.toInt(),
                    size.height.toInt(),
                    spec
                )
            }
        }

        // Top row: back + favorite, floating glass circles
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween
        ) {
            CircleIconButton(icon = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back") {
                onBack()
            }
            CircleIconButton(
                icon = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                contentDescription = "Toggle favorite",
                tint = if (isFavorite) Color(0xFFFF6B9D) else Color.White
            ) {
                viewModel.toggleFavorite(spec.id)
            }
        }

        // Bottom glass control panel
        GlassCard(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(16.dp),
            cornerRadius = 26.dp,
            fillAlpha = 0.24f
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = spec.name,
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = spec.category.displayName,
                    color = Color.White.copy(alpha = 0.8f),
                    style = MaterialTheme.typography.bodySmall
                )

                androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(top = 14.dp))

                // Home / Lock / Both segmented selector
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)
                ) {
                    TargetOption("Home", target == WallpaperTarget.HOME, Modifier.weight(1f)) {
                        target = WallpaperTarget.HOME
                    }
                    TargetOption("Lock", target == WallpaperTarget.LOCK, Modifier.weight(1f)) {
                        target = WallpaperTarget.LOCK
                    }
                    TargetOption("Both", target == WallpaperTarget.BOTH, Modifier.weight(1f)) {
                        target = WallpaperTarget.BOTH
                    }
                }

                androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(top = 16.dp))

                GlossyButton(
                    text = if (isApplying) "Setting..." else "Set Wallpaper",
                    onClick = {
                        if (!isApplying) {
                            isApplying = true
                            scope.launch {
                                val success = withContext(Dispatchers.Default) {
                                    WallpaperSetter.apply(context, spec, target)
                                }
                                isApplying = false
                                statusMessage = if (success) "Wallpaper set!" else "Couldn't set wallpaper."
                                if (success) {
                                    showSuccess = true
                                }
                            }
                        }
                    },
                    enabled = !isApplying,
                    fillMaxWidth = true,
                    leadingIcon = if (isApplying) {
                        {
                            CircularProgressIndicator(
                                modifier = Modifier.size(16.dp),
                                color = Color.White,
                                strokeWidth = 2.dp
                            )
                            androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(end = 8.dp))
                        }
                    } else null
                )

                androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(top = 10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(enabled = !isSaving) {
                            isSaving = true
                            scope.launch {
                                val bitmap = withContext(Dispatchers.Default) {
                                    val metrics = context.resources.displayMetrics
                                    WallpaperRenderer.render(
                                        metrics.widthPixels,
                                        metrics.heightPixels,
                                        spec
                                    )
                                }
                                val saved = withContext(Dispatchers.IO) {
                                    MediaSaver.saveToGallery(context, bitmap, spec.id)
                                }
                                isSaving = false
                                statusMessage = if (saved) "Saved to Pictures/Aeroscape" else "Couldn't save image."
                            }
                        }
                        .padding(vertical = 8.dp),
                    horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Download,
                        contentDescription = null,
                        tint = Color.White.copy(alpha = 0.9f),
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = if (isSaving) "  Saving..." else "  Save to device",
                        color = Color.White.copy(alpha = 0.9f),
                        style = MaterialTheme.typography.labelLarge
                    )
                }

                statusMessage?.let { message ->
                    Text(
                        text = message,
                        color = Color.White.copy(alpha = 0.85f),
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(top = 6.dp)
                    )
                }
            }
        }

        // Success confirmation overlay
        AnimatedVisibility(
            visible = showSuccess,
            enter = fadeIn(tween(200)) + scaleIn(tween(200), initialScale = 0.8f),
            exit = fadeOut(tween(300)) + scaleOut(tween(300), targetScale = 0.8f),
            modifier = Modifier.align(Alignment.Center)
        ) {
            Box(
                modifier = Modifier
                    .size(84.dp)
                    .clip(CircleShape)
                    .background(Color.Black.copy(alpha = 0.55f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }

    // Auto-dismiss the success checkmark
    androidx.compose.runtime.LaunchedEffect(showSuccess) {
        if (showSuccess) {
            kotlinx.coroutines.delay(1100)
            showSuccess = false
        }
    }
}

@Composable
private fun CircleIconButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    contentDescription: String,
    tint: Color = Color.White,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(44.dp)
            .clip(CircleShape)
            .background(Color.Black.copy(alpha = 0.28f))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(imageVector = icon, contentDescription = contentDescription, tint = tint)
    }
}

@Composable
private fun TargetOption(
    label: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(14.dp)
    Box(
        modifier = modifier
            .clip(shape)
            .background(if (selected) Color.White.copy(alpha = 0.9f) else Color.White.copy(alpha = 0.18f))
            .clickable { onClick() }
            .padding(vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            color = if (selected) Color(0xFF0072FF) else Color.White,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
            style = MaterialTheme.typography.labelLarge
        )
    }
}
