package com.aeroscape.wallpapers.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightScheme = lightColorScheme(
    primary = SkyBlueMid,
    onPrimary = Color.White,
    secondary = AeroGreen,
    tertiary = VaporLilac,
    background = CreamBg,
    onBackground = InkNavy,
    surface = GlassWhite,
    onSurface = InkNavy
)

private val DarkScheme = darkColorScheme(
    primary = SkyBlueMid,
    onPrimary = Color.White,
    secondary = AeroGreen,
    tertiary = VaporLilac,
    background = InkNavy,
    onBackground = CreamBg,
    surface = InkNavy,
    onSurface = CreamBg
)

@Composable
fun AeroscapeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkScheme else LightScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = AeroTypography,
        content = content
    )
}
