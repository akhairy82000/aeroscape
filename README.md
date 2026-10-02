# Aeroscape — Aero Wallpapers

A native Android app for browsing, previewing, and setting Frutiger Aero / Y2K /
glassmorphism-inspired wallpapers. Built with Kotlin + Jetpack Compose.

## Why this app looks the way it does

Every wallpaper in this app is **generated in code**, not loaded from an image
file. `WallpaperRenderer.kt` takes a small "recipe" (a gradient + a few glossy
"orb" highlights + an optional perspective grid for the vaporwave sets) and
draws it fresh, both for the small grid thumbnails and for the final
wallpaper at your exact screen resolution.

This was a deliberate choice, not a shortcut:
- **Zero copyright risk** — nothing here is a scraped or stock photo.
- **Tiny app size** — no bundled image assets at all.
- **Trivial to extend** — add a new wallpaper by adding ~10 lines of color
  values to `WallpaperCatalog.kt`, no image editing required.
- It's also true to the aesthetic itself — Frutiger Aero was always about
  gradients, glass, and light, not photography.

There are **20 wallpapers across 5 style families**:

| Category | Vibe |
|---|---|
| Aero Classic | Glossy blue-sky gradients, soft white cloud highlights (classic Vista/XP "Bliss" energy) |
| Water Drop | Deep teal/navy fields with small droplet-like glossy highlights |
| Chrome Orb | Brushed-metal / liquid-mercury grays with a glossy sphere highlight |
| Vapor Sky | Y2K vaporwave sunsets with a receding perspective grid |
| Bubble Dream | Soft pastel bubble clusters on a cream background |

## What the app does

- Browse all wallpapers in a two-column grid, filterable by category
- Tap a wallpaper for a full-screen preview
- Set it as your **Home screen**, **Lock screen**, or **Both**
- Save it to `Pictures/Aeroscape` on your device
- Favorite wallpapers, saved locally (persists across app restarts)
- A frosted-glass UI with floating bubble animations throughout

## Project structure

```
Aeroscape/
├── app/src/main/java/com/aeroscape/wallpapers/
│   ├── MainActivity.kt              Entry point
│   ├── data/
│   │   ├── WallpaperSpec.kt         The data model (colors, orbs, etc.)
│   │   └── WallpaperCatalog.kt      All 20 wallpaper recipes — edit this to add more
│   ├── render/
│   │   └── WallpaperRenderer.kt     Turns a recipe into pixels (gradients, glass, grid)
│   ├── util/
│   │   ├── WallpaperSetter.kt       Calls WallpaperManager to actually set the wallpaper
│   │   └── MediaSaver.kt            Saves a rendered wallpaper to the gallery
│   └── ui/
│       ├── theme/                   Colors, typography, Material3 theme
│       ├── components/              GlassCard, GlossyButton, BubbleBackdrop, grid, etc.
│       ├── screens/                 GalleryScreen, FavoritesScreen, PreviewScreen
│       ├── navigation/               Bottom nav + NavHost wiring
│       └── viewmodel/                Favorites + category filter state
└── app/src/main/res/                Manifest resources, adaptive launcher icon
```

## Prerequisites

- **Android Studio** (Koala/2024.1 or newer recommended) — this is by far the
  easiest way to build and run this, especially if you haven't done Android
  development before. Download: https://developer.android.com/studio
- JDK 17 (Android Studio bundles its own, so you usually don't need to
  install this separately)
- An Android device or emulator running **Android 8.0 (API 26) or newer**

## Building and running it

The simplest path, since you're starting from zero:

1. Unzip this project anywhere on your computer.
2. Open Android Studio → **File → Open** → select the unzipped `Aeroscape`
   folder.
3. Wait for Gradle to sync (Android Studio will download the Android SDK
   platform and dependencies automatically the first time — this can take a
   few minutes).
4. Create a device to run it on: **Tools → Device Manager → Create device**
   (pick any phone profile, any recent Android version), or plug in a real
   Android phone with USB debugging enabled.
5. Click the green **Run ▶** button in the toolbar.

The app should build and launch, landing on the wallpaper grid.

### Command line (optional)

A real Gradle wrapper is already included, so if you have the Android SDK
installed and `ANDROID_HOME` set, you can also do:

```bash
./gradlew assembleDebug
```

The built APK will be at `app/build/outputs/apk/debug/app-debug.apk`.

## Adding more wallpapers

Open `WallpaperCatalog.kt` and add a new `WallpaperSpec` entry, e.g.:

```kotlin
WallpaperSpec(
    id = "my_new_wallpaper",
    name = "My New Wallpaper",
    category = WallpaperCategory.BUBBLE_DREAM,
    backgroundColors = intArrayOf(col("#FFEEF5"), col("#FFD1E8")),
    backgroundStops = floatArrayOf(0f, 1f),
    orbs = listOf(
        Orb(0.5f, 0.4f, 0.22f, intArrayOf(col("#B3FF9AD5"), col("#00FF9AD5")))
    )
)
```

- `cx`/`cy`/`radius` on an `Orb` are fractions of width/height (0..1), so they
  scale to any screen size automatically.
- Orb colors go from an **opaque** core color to a **fully transparent**
  version of the same color (`Color.parseColor` accepts 8-digit
  `#AARRGGBB` hex — the first two digits are alpha).
- Set `showGrid = true` and pick a `gridColor` for a vaporwave-style
  perspective grid.

No image assets, no re-export step — just rebuild and it shows up in the grid.

## Notes on permissions

The app only requests `WRITE_EXTERNAL_STORAGE`, and only on Android 9 and
below (`maxSdkVersion="28"` in the manifest) — Android 10+ uses the scoped
`MediaStore` API for saving images, which needs no special permission.
Setting the wallpaper itself needs no permission at all.

## Where to take this next

A few natural next steps, in roughly the order I'd tackle them:

1. **Polish pass** — real device testing, tweak a few gradients that look
   too subtle/strong on an actual screen.
2. **Live wallpaper** (an actual `WallpaperService` with a slow drifting
   animation of the orbs) instead of a static image, using the exact same
   `WallpaperSpec` recipes — this would be a genuinely differentiating
   feature versus other wallpaper apps.
3. **Monetization** — an interstitial or banner ad (Google AdMob) on the
   preview screen, plus a small one-time "Pro" unlock for a handful of
   exclusive recipes.
4. **Play Store listing** — you'll need a developer account ($25 one-time),
   a privacy policy (simple, since this app collects no personal data), and
   a few screenshots — the preview screen itself makes for good screenshots.
5. **Browser extension** — once the app has some traction, the same
   procedural-rendering approach ports well to a "new tab" browser extension
   showing the same wallpapers.
