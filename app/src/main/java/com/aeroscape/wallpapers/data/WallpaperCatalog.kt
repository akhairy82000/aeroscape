package com.aeroscape.wallpapers.data

import android.graphics.Color

/**
 * The full wallpaper library. Every entry is a color-and-geometry "recipe" --
 * there are no image assets anywhere in this app. Add a new WallpaperSpec
 * here to add a new wallpaper; WallpaperRenderer does the rest.
 */
object WallpaperCatalog {

    private fun col(hex: String) = Color.parseColor(hex)

    val all: List<WallpaperSpec> = listOf(

        // ---------------- AERO CLASSIC ----------------
        WallpaperSpec(
            id = "aero_dawn",
            name = "Aero Dawn",
            category = WallpaperCategory.AERO_CLASSIC,
            backgroundColors = intArrayOf(col("#BFE9FF"), col("#4FACFE"), col("#0072FF")),
            backgroundStops = floatArrayOf(0f, 0.5f, 1f),
            orbs = listOf(
                Orb(0.22f, 0.18f, 0.30f, intArrayOf(col("#F2FFFFFF"), col("#00FFFFFF"))),
                Orb(0.82f, 0.70f, 0.22f, intArrayOf(col("#E6FFFFFF"), col("#00FFFFFF")), alpha = 0.9f),
                Orb(0.5f, 0.97f, 0.55f, intArrayOf(col("#66A8E6CF"), col("#00A8E6CF")), alpha = 0.8f)
            )
        ),
        WallpaperSpec(
            id = "glass_horizon",
            name = "Glass Horizon",
            category = WallpaperCategory.AERO_CLASSIC,
            backgroundColors = intArrayOf(col("#E8F7FF"), col("#63B9FF"), col("#1E5FBF")),
            backgroundStops = floatArrayOf(0f, 0.5f, 1f),
            orbs = listOf(
                Orb(0.30f, 0.22f, 0.26f, intArrayOf(col("#E6FFFFFF"), col("#00FFFFFF"))),
                Orb(0.68f, 0.15f, 0.16f, intArrayOf(col("#CCFFFFFF"), col("#00FFFFFF")), alpha = 0.8f),
                Orb(0.5f, 0.88f, 0.42f, intArrayOf(col("#5500C6FB"), col("#0000C6FB")), alpha = 0.7f)
            )
        ),
        WallpaperSpec(
            id = "vista_bliss",
            name = "Vista Bliss",
            category = WallpaperCategory.AERO_CLASSIC,
            backgroundColors = intArrayOf(col("#CFF3D8"), col("#8FE3C0"), col("#3AAFD9")),
            backgroundStops = floatArrayOf(0f, 0.5f, 1f),
            orbs = listOf(
                Orb(0.25f, 0.20f, 0.30f, intArrayOf(col("#F2FFFFFF"), col("#00FFFFFF"))),
                Orb(0.5f, 0.93f, 0.60f, intArrayOf(col("#66A8E6CF"), col("#00A8E6CF")), alpha = 0.8f),
                Orb(0.78f, 0.55f, 0.14f, intArrayOf(col("#CCFFFFFF"), col("#00FFFFFF")), alpha = 0.7f)
            )
        ),
        WallpaperSpec(
            id = "morning_chrome",
            name = "Morning Chrome",
            category = WallpaperCategory.AERO_CLASSIC,
            backgroundColors = intArrayOf(col("#E9F4FF"), col("#A7D8FF"), col("#4FACFE")),
            backgroundStops = floatArrayOf(0f, 0.5f, 1f),
            gradientDir = 0.3f to 1f,
            orbs = listOf(
                Orb(0.5f, 0.45f, 0.32f, intArrayOf(col("#FFFFFFFF"), col("#00FFFFFF"))),
                Orb(0.15f, 0.80f, 0.18f, intArrayOf(col("#4D14789E"), col("#0014789E")), alpha = 0.8f)
            )
        ),

        // ---------------- WATER DROP ----------------
        WallpaperSpec(
            id = "droplet_deep",
            name = "Droplet Deep",
            category = WallpaperCategory.WATER_DROP,
            backgroundColors = intArrayOf(col("#062B4A"), col("#0B4C77"), col("#14789E")),
            backgroundStops = floatArrayOf(0f, 0.5f, 1f),
            orbs = listOf(
                Orb(0.5f, 0.85f, 0.5f, intArrayOf(col("#3314789E"), col("#0014789E")), alpha = 0.8f),
                Orb(0.30f, 0.25f, 0.12f, intArrayOf(col("#E6FFFFFF"), col("#00FFFFFF"))),
                Orb(0.60f, 0.40f, 0.07f, intArrayOf(col("#F2FFFFFF"), col("#00FFFFFF"))),
                Orb(0.75f, 0.65f, 0.10f, intArrayOf(col("#CC9AD9E8"), col("#009AD9E8"))),
                Orb(0.20f, 0.70f, 0.05f, intArrayOf(col("#F2FFFFFF"), col("#00FFFFFF")))
            )
        ),
        WallpaperSpec(
            id = "ripple_teal",
            name = "Ripple Teal",
            category = WallpaperCategory.WATER_DROP,
            backgroundColors = intArrayOf(col("#003049"), col("#005F73"), col("#0A9396")),
            backgroundStops = floatArrayOf(0f, 0.5f, 1f),
            orbs = listOf(
                Orb(0.5f, 0.9f, 0.5f, intArrayOf(col("#330A9396"), col("#000A9396")), alpha = 0.8f),
                Orb(0.25f, 0.30f, 0.10f, intArrayOf(col("#E6FFFFFF"), col("#00FFFFFF"))),
                Orb(0.65f, 0.22f, 0.06f, intArrayOf(col("#F2FFFFFF"), col("#00FFFFFF"))),
                Orb(0.80f, 0.55f, 0.09f, intArrayOf(col("#CC9AD9E8"), col("#009AD9E8"))),
                Orb(0.40f, 0.68f, 0.05f, intArrayOf(col("#F2FFFFFF"), col("#00FFFFFF")))
            )
        ),
        WallpaperSpec(
            id = "blue_bead",
            name = "Blue Bead",
            category = WallpaperCategory.WATER_DROP,
            backgroundColors = intArrayOf(col("#001F3F"), col("#0B3D91"), col("#1E90FF")),
            backgroundStops = floatArrayOf(0f, 0.5f, 1f),
            orbs = listOf(
                Orb(0.5f, 0.5f, 0.28f, intArrayOf(col("#991E90FF"), col("#001E90FF"))),
                Orb(0.42f, 0.40f, 0.08f, intArrayOf(col("#F2FFFFFF"), col("#00FFFFFF"))),
                Orb(0.5f, 0.95f, 0.5f, intArrayOf(col("#330B3D91"), col("#000B3D91")), alpha = 0.7f)
            )
        ),
        WallpaperSpec(
            id = "ocean_glass",
            name = "Ocean Glass",
            category = WallpaperCategory.WATER_DROP,
            backgroundColors = intArrayOf(col("#012A4A"), col("#01497C"), col("#2C7DA0")),
            backgroundStops = floatArrayOf(0f, 0.5f, 1f),
            orbs = listOf(
                Orb(0.20f, 0.20f, 0.08f, intArrayOf(col("#E6FFFFFF"), col("#00FFFFFF"))),
                Orb(0.45f, 0.35f, 0.05f, intArrayOf(col("#F2FFFFFF"), col("#00FFFFFF"))),
                Orb(0.68f, 0.28f, 0.06f, intArrayOf(col("#CC9AD9E8"), col("#009AD9E8"))),
                Orb(0.80f, 0.60f, 0.09f, intArrayOf(col("#E6FFFFFF"), col("#00FFFFFF"))),
                Orb(0.5f, 0.92f, 0.55f, intArrayOf(col("#332C7DA0"), col("#002C7DA0")), alpha = 0.8f)
            )
        ),

        // ---------------- CHROME ORB ----------------
        WallpaperSpec(
            id = "brushed_steel",
            name = "Brushed Steel",
            category = WallpaperCategory.CHROME_ORB,
            backgroundColors = intArrayOf(col("#D9DEE3"), col("#AAB4BD"), col("#7A8894")),
            backgroundStops = floatArrayOf(0f, 0.5f, 1f),
            gradientDir = 1f to 0.6f,
            orbs = listOf(
                Orb(0.5f, 0.42f, 0.30f, intArrayOf(col("#F2FFFFFF"), col("#00FFFFFF"))),
                Orb(0.78f, 0.75f, 0.12f, intArrayOf(col("#664FACFE"), col("#004FACFE")), alpha = 0.8f)
            )
        ),
        WallpaperSpec(
            id = "liquid_mercury",
            name = "Liquid Mercury",
            category = WallpaperCategory.CHROME_ORB,
            backgroundColors = intArrayOf(col("#C6CDD3"), col("#8D97A0"), col("#4B5A67")),
            backgroundStops = floatArrayOf(0f, 0.5f, 1f),
            orbs = listOf(
                Orb(0.5f, 0.5f, 0.22f, intArrayOf(col("#FFFFFFFF"), col("#00FFFFFF"))),
                Orb(0.2f, 0.75f, 0.18f, intArrayOf(col("#99FFFFFF"), col("#00FFFFFF")), alpha = 0.6f)
            )
        ),
        WallpaperSpec(
            id = "platinum_orb",
            name = "Platinum Orb",
            category = WallpaperCategory.CHROME_ORB,
            backgroundColors = intArrayOf(col("#EDEFF2"), col("#C3C9CF"), col("#8A94A0")),
            backgroundStops = floatArrayOf(0f, 0.5f, 1f),
            orbs = listOf(
                Orb(0.45f, 0.4f, 0.34f, intArrayOf(col("#E6FFFFFF"), col("#00FFFFFF"))),
                Orb(0.75f, 0.3f, 0.10f, intArrayOf(col("#5514789E"), col("#0014789E")), alpha = 0.7f)
            )
        ),
        WallpaperSpec(
            id = "titanium_sky",
            name = "Titanium Sky",
            category = WallpaperCategory.CHROME_ORB,
            backgroundColors = intArrayOf(col("#B9C6D6"), col("#6E8CA8"), col("#345B7A")),
            backgroundStops = floatArrayOf(0f, 0.5f, 1f),
            orbs = listOf(
                Orb(0.5f, 0.35f, 0.28f, intArrayOf(col("#E6FFFFFF"), col("#00FFFFFF"))),
                Orb(0.3f, 0.85f, 0.4f, intArrayOf(col("#334FACFE"), col("#004FACFE")), alpha = 0.7f)
            )
        ),

        // ---------------- VAPOR SKY ----------------
        WallpaperSpec(
            id = "retro_sunset",
            name = "Retro Sunset",
            category = WallpaperCategory.VAPOR_SKY,
            backgroundColors = intArrayOf(col("#2E1A47"), col("#FF6AD5"), col("#FFA36C")),
            backgroundStops = floatArrayOf(0f, 0.5f, 1f),
            showGrid = true,
            gridColor = Color.parseColor("#FFFFFFFF"),
            orbs = listOf(
                Orb(0.5f, 0.60f, 0.40f, intArrayOf(col("#66FF6AD5"), col("#00FF6AD5"))),
                Orb(0.5f, 0.60f, 0.26f, intArrayOf(col("#FFFFFDE7"), col("#00FFFDE7")))
            )
        ),
        WallpaperSpec(
            id = "neon_mirage",
            name = "Neon Mirage",
            category = WallpaperCategory.VAPOR_SKY,
            backgroundColors = intArrayOf(col("#1B0034"), col("#7000FF"), col("#FF00C8")),
            backgroundStops = floatArrayOf(0f, 0.5f, 1f),
            showGrid = true,
            gridColor = Color.parseColor("#FFFFFFFF"),
            orbs = listOf(
                Orb(0.5f, 0.55f, 0.35f, intArrayOf(col("#66FF00C8"), col("#00FF00C8"))),
                Orb(0.25f, 0.30f, 0.15f, intArrayOf(col("#6600F0FF"), col("#0000F0FF")))
            )
        ),
        WallpaperSpec(
            id = "chrome_sunset",
            name = "Chrome Sunset",
            category = WallpaperCategory.VAPOR_SKY,
            backgroundColors = intArrayOf(col("#240046"), col("#C9184A"), col("#FF7B54")),
            backgroundStops = floatArrayOf(0f, 0.5f, 1f),
            showGrid = true,
            gridColor = Color.parseColor("#FFFFFFFF"),
            orbs = listOf(
                Orb(0.5f, 0.58f, 0.30f, intArrayOf(col("#80FFD9B3"), col("#00FFD9B3")))
            )
        ),
        WallpaperSpec(
            id = "digital_dusk",
            name = "Digital Dusk",
            category = WallpaperCategory.VAPOR_SKY,
            backgroundColors = intArrayOf(col("#10002B"), col("#5A189A"), col("#E0AAFF")),
            backgroundStops = floatArrayOf(0f, 0.5f, 1f),
            showGrid = true,
            gridColor = Color.parseColor("#FFFFFFFF"),
            orbs = listOf(
                Orb(0.5f, 0.5f, 0.32f, intArrayOf(col("#66E0AAFF"), col("#00E0AAFF"))),
                Orb(0.75f, 0.2f, 0.05f, intArrayOf(col("#FFFFFFFF"), col("#00FFFFFF")))
            )
        ),

        // ---------------- BUBBLE DREAM ----------------
        WallpaperSpec(
            id = "cotton_cloud",
            name = "Cotton Cloud",
            category = WallpaperCategory.BUBBLE_DREAM,
            backgroundColors = intArrayOf(col("#FFF6F0"), col("#FDEBD3")),
            backgroundStops = floatArrayOf(0f, 1f),
            gradientDir = 1f to 0.4f,
            orbs = listOf(
                Orb(0.25f, 0.30f, 0.16f, intArrayOf(col("#B3FFD6E8"), col("#00FFD6E8"))),
                Orb(0.68f, 0.22f, 0.14f, intArrayOf(col("#B3D2FBEB"), col("#00D2FBEB"))),
                Orb(0.50f, 0.68f, 0.20f, intArrayOf(col("#B3E5D4FF"), col("#00E5D4FF"))),
                Orb(0.82f, 0.75f, 0.12f, intArrayOf(col("#B3FFF3B0"), col("#00FFF3B0")))
            )
        ),
        WallpaperSpec(
            id = "sherbet_pop",
            name = "Sherbet Pop",
            category = WallpaperCategory.BUBBLE_DREAM,
            backgroundColors = intArrayOf(col("#FFF0F5"), col("#FFE8EF")),
            backgroundStops = floatArrayOf(0f, 1f),
            orbs = listOf(
                Orb(0.28f, 0.25f, 0.15f, intArrayOf(col("#B3FF8C69"), col("#00FF8C69"))),
                Orb(0.70f, 0.30f, 0.14f, intArrayOf(col("#B340E0D0"), col("#0040E0D0"))),
                Orb(0.5f, 0.72f, 0.20f, intArrayOf(col("#B3FFF44F"), col("#00FFF44F")))
            )
        ),
        WallpaperSpec(
            id = "marshmallow",
            name = "Marshmallow",
            category = WallpaperCategory.BUBBLE_DREAM,
            backgroundColors = intArrayOf(col("#FFFDF7"), col("#FFF1E6")),
            backgroundStops = floatArrayOf(0f, 1f),
            orbs = listOf(
                Orb(0.30f, 0.30f, 0.22f, intArrayOf(col("#CCFFFFFF"), col("#00FFFFFF"))),
                Orb(0.70f, 0.65f, 0.20f, intArrayOf(col("#B3FFD6E8"), col("#00FFD6E8"))),
                Orb(0.20f, 0.75f, 0.10f, intArrayOf(col("#B3D2FBEB"), col("#00D2FBEB")))
            )
        ),
        WallpaperSpec(
            id = "bubblegum_bloom",
            name = "Bubblegum Bloom",
            category = WallpaperCategory.BUBBLE_DREAM,
            backgroundColors = intArrayOf(col("#FFEAF4"), col("#FFD6ED")),
            backgroundStops = floatArrayOf(0f, 1f),
            orbs = listOf(
                Orb(0.5f, 0.35f, 0.24f, intArrayOf(col("#B3FF9AD5"), col("#00FF9AD5"))),
                Orb(0.20f, 0.75f, 0.14f, intArrayOf(col("#B3AAF0D1"), col("#00AAF0D1"))),
                Orb(0.80f, 0.70f, 0.10f, intArrayOf(col("#B3E5D4FF"), col("#00E5D4FF")))
            )
        )
    )

    fun byId(id: String): WallpaperSpec? = all.find { it.id == id }

    fun byCategory(category: WallpaperCategory?): List<WallpaperSpec> =
        if (category == null) all else all.filter { it.category == category }
}
