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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aeroscape.wallpapers.data.WallpaperSpec
import com.aeroscape.wallpapers.ui.components.BubbleBackdrop
import com.aeroscape.wallpapers.ui.components.CategoryChipsRow
import com.aeroscape.wallpapers.ui.components.WallpaperGrid
import com.aeroscape.wallpapers.ui.theme.SkyBlueDeep
import com.aeroscape.wallpapers.ui.theme.SkyBlueLight
import com.aeroscape.wallpapers.ui.theme.SkyBlueMid
import com.aeroscape.wallpapers.ui.viewmodel.WallpaperViewModel

@Composable
fun GalleryScreen(
    viewModel: WallpaperViewModel,
    onWallpaperClick: (WallpaperSpec) -> Unit
) {
    val wallpapers by viewModel.filteredWallpapers.collectAsStateWithLifecycle()
    val favorites by viewModel.favorites.collectAsStateWithLifecycle()
    val selectedCategory by viewModel.selectedCategory.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(listOf(SkyBlueLight, SkyBlueMid, SkyBlueDeep))
            )
    ) {
        BubbleBackdrop(modifier = Modifier.fillMaxSize())

        Column(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(start = 20.dp, end = 20.dp, top = 12.dp, bottom = 4.dp)
            ) {
                Text(
                    text = "Aeroscape",
                    color = androidx.compose.ui.graphics.Color.White,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Glassy, nostalgic wallpapers",
                    color = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.85f),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(top = 6.dp))

            CategoryChipsRow(
                selected = selectedCategory,
                onSelect = { viewModel.setCategory(it) },
                modifier = Modifier.padding(vertical = 8.dp)
            )

            WallpaperGrid(
                wallpapers = wallpapers,
                favorites = favorites,
                onWallpaperClick = onWallpaperClick,
                onToggleFavorite = { viewModel.toggleFavorite(it.id) },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
