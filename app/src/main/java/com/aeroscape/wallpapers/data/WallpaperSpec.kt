package com.aeroscape.wallpapers.data

import android.graphics.Color

enum class WallpaperCategory(val displayName: String) {
    AERO_CLASSIC("Aero Classic"),
    WATER_DROP("Water Drop"),
    CHROME_ORB("Chrome Orb"),
    VAPOR_SKY("Vapor Sky"),
    BUBBLE_DREAM("Bubble Dream")
}

/**
 * A single glossy highlight / blob, drawn as a radial gradient.
 * [colors] should go from an opaque tint at the core to a fully transparent
 * edge of the same hue (e.g. "#F2FFFFFF" -> "#00FFFFFF").
 */
data class Orb(
    val cx: Float,
    val cy: Float,
    val radius: Float,
    val colors: IntArray,
    val stops: FloatArray = floatArrayOf(0f, 1f),
    val alpha: Float = 1f
)

/**
 * Full description of one procedurally-rendered wallpaper. Nothing here is a
 * bitmap asset -- WallpaperRenderer turns this recipe into pixels on demand,
 * both for grid thumbnails and for the final high-resolution wallpaper.
 */
data class WallpaperSpec(
    val id: String,
    val name: String,
    val category: WallpaperCategory,
    val backgroundColors: IntArray,
    val backgroundStops: FloatArray,
    val gradientDir: Pair<Float, Float> = 0f to 1f,
    val orbs: List<Orb> = emptyList(),
    val showGrid: Boolean = false,
    val gridColor: Int = Color.WHITE
)
