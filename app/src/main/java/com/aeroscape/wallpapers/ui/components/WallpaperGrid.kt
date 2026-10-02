package com.aeroscape.wallpapers.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aeroscape.wallpapers.data.WallpaperSpec

@Composable
fun WallpaperGrid(
    wallpapers: List<WallpaperSpec>,
    favorites: Set<String>,
    onWallpaperClick: (WallpaperSpec) -> Unit,
    onToggleFavorite: (WallpaperSpec) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(16.dp)
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
        contentPadding = contentPadding,
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        items(wallpapers, key = { it.id }) { spec ->
            WallpaperThumbnail(
                spec = spec,
                isFavorite = spec.id in favorites,
                onClick = { onWallpaperClick(spec) },
                onToggleFavorite = { onToggleFavorite(spec) }
            )
        }
    }
}
