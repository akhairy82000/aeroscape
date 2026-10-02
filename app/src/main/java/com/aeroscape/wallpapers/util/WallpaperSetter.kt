package com.aeroscape.wallpapers.util

import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import com.aeroscape.wallpapers.data.WallpaperSpec
import com.aeroscape.wallpapers.render.WallpaperRenderer

enum class WallpaperTarget { HOME, LOCK, BOTH }

object WallpaperSetter {

    /**
     * Renders [spec] at the device's actual screen resolution and applies it
     * via WallpaperManager. Safe to call from a background thread.
     */
    fun apply(context: Context, spec: WallpaperSpec, target: WallpaperTarget): Boolean {
        return try {
            val metrics = context.resources.displayMetrics
            val bitmap: Bitmap = WallpaperRenderer.render(
                metrics.widthPixels,
                metrics.heightPixels,
                spec
            )
            val manager = WallpaperManager.getInstance(context)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val flags = when (target) {
                    WallpaperTarget.HOME -> WallpaperManager.FLAG_SYSTEM
                    WallpaperTarget.LOCK -> WallpaperManager.FLAG_LOCK
                    WallpaperTarget.BOTH -> WallpaperManager.FLAG_SYSTEM or WallpaperManager.FLAG_LOCK
                }
                manager.setBitmap(bitmap, null, true, flags)
            } else {
                manager.setBitmap(bitmap)
            }
            true
        } catch (e: Exception) {
            false
        }
    }
}
