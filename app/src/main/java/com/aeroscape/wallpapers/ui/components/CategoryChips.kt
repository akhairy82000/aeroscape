package com.aeroscape.wallpapers.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aeroscape.wallpapers.data.WallpaperCategory

/**
 * A horizontally scrolling row of filter chips: "All" plus one per
 * WallpaperCategory. The selected chip gets a solid glossy fill; the
 * rest stay as faint glass pills.
 */
@Composable
fun CategoryChipsRow(
    selected: WallpaperCategory?,
    onSelect: (WallpaperCategory?) -> Unit,
    modifier: Modifier = Modifier
) {
    val categories: List<WallpaperCategory?> = listOf(null) + WallpaperCategory.entries

    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 20.dp),
        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(10.dp)
    ) {
        items(categories) { category ->
            val isSelected = category == selected
            val label = category?.displayName ?: "All"
            val shape = RoundedCornerShape(50)

            Row(
                modifier = Modifier
                    .clip(shape)
                    .background(
                        if (isSelected) MaterialTheme.colorScheme.primary
                        else Color.White.copy(alpha = 0.20f)
                    )
                    .clickable { onSelect(category) }
                    .padding(horizontal = 18.dp, vertical = 9.dp)
            ) {
                Text(
                    text = label,
                    color = if (isSelected) Color.White else MaterialTheme.colorScheme.onBackground,
                    fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}
