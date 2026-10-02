package com.aeroscape.wallpapers.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aeroscape.wallpapers.data.WallpaperSpec
import com.aeroscape.wallpapers.ui.components.WallpaperGrid
import com.aeroscape.wallpapers.ui.theme.VaporLilac
import com.aeroscape.wallpapers.ui.theme.SkyBlueDeep
import com.aeroscape.wallpapers.ui.viewmodel.WallpaperViewModel

@Composable
fun FavoritesScreen(
    viewModel: WallpaperViewModel,
    onWallpaperClick: (WallpaperSpec) -> Unit
) {
    val favoriteList by viewModel.favoriteWallpapers.collectAsStateWithLifecycle()
    val favorites by viewModel.favorites.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(VaporLilac, SkyBlueDeep)))
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Favorites",
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(start = 20.dp, end = 20.dp, top = 12.dp, bottom = 8.dp)
            )

            if (favoriteList.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = "Tap the heart on any wallpaper\nto save it here.",
                        color = Color.White.copy(alpha = 0.85f),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
            } else {
                WallpaperGrid(
                    wallpapers = favoriteList,
                    favorites = favorites,
                    onWallpaperClick = onWallpaperClick,
                    onToggleFavorite = { viewModel.toggleFavorite(it.id) },
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
