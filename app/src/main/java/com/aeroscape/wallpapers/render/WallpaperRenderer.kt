package com.aeroscape.wallpapers.render

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.RadialGradient
import android.graphics.Shader
import com.aeroscape.wallpapers.data.WallpaperSpec

/**
 * Turns a [WallpaperSpec] recipe into actual pixels. Used both for the small
 * live-drawn thumbnails in the gallery grid and for the full-resolution
 * bitmap that gets handed to WallpaperManager -- same code path, different
 * canvas size, so what you preview is exactly what gets set.
 */
object WallpaperRenderer {

    /** Renders a brand new bitmap at the requested resolution. */
    fun render(width: Int, height: Int, spec: WallpaperSpec): Bitmap {
        val safeWidth = width.coerceAtLeast(1)
        val safeHeight = height.coerceAtLeast(1)
        val bitmap = Bitmap.createBitmap(safeWidth, safeHeight, Bitmap.Config.ARGB_8888)
        draw(Canvas(bitmap), safeWidth, safeHeight, spec)
        return bitmap
    }

    /** Draws the wallpaper directly onto an existing canvas (Compose or native). */
    fun draw(canvas: Canvas, width: Int, height: Int, spec: WallpaperSpec) {
        val w = width.toFloat()
        val h = height.toFloat()
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)

        // 1. Base gradient
        paint.shader = LinearGradient(
            0f, 0f, w * spec.gradientDir.first, h * spec.gradientDir.second,
            spec.backgroundColors, spec.backgroundStops, Shader.TileMode.CLAMP
        )
        canvas.drawRect(0f, 0f, w, h, paint)

        // 2. Optional vaporwave perspective grid, drawn under the orbs
        if (spec.showGrid) {
            drawGrid(canvas, w, h, spec.gridColor)
        }

        // 3. Glossy orbs / blobs
        spec.orbs.forEach { orb ->
            val orbPaint = Paint(Paint.ANTI_ALIAS_FLAG)
            orbPaint.shader = RadialGradient(
                w * orb.cx, h * orb.cy, (w * orb.radius).coerceAtLeast(1f),
                orb.colors, orb.stops, Shader.TileMode.CLAMP
            )
            orbPaint.alpha = (orb.alpha * 255f).toInt().coerceIn(0, 255)
            canvas.drawCircle(w * orb.cx, h * orb.cy, w * orb.radius, orbPaint)
        }

        // 4. Diagonal glass shine streak -- the signature Aero touch
        val shinePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        shinePaint.shader = LinearGradient(
            0f, 0f, w * 0.65f, h,
            intArrayOf(
                Color.argb(0, 255, 255, 255),
                Color.argb(40, 255, 255, 255),
                Color.argb(0, 255, 255, 255)
            ),
            floatArrayOf(0.30f, 0.48f, 0.66f),
            Shader.TileMode.CLAMP
        )
        canvas.drawRect(0f, 0f, w, h, shinePaint)

        // 5. Soft bottom vignette so home-screen icons stay legible
        val vignette = Paint(Paint.ANTI_ALIAS_FLAG)
        vignette.shader = LinearGradient(
            0f, h * 0.55f, 0f, h,
            intArrayOf(Color.argb(0, 0, 0, 0), Color.argb(90, 0, 0, 0)),
            null, Shader.TileMode.CLAMP
        )
        canvas.drawRect(0f, h * 0.55f, w, h, vignette)
    }

    private fun drawGrid(canvas: Canvas, w: Float, h: Float, gridColor: Int) {
        val gridPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        gridPaint.color = gridColor
        gridPaint.style = Paint.Style.STROKE
        gridPaint.strokeWidth = w * 0.0035f
        gridPaint.alpha = 130

        val horizonY = h * 0.72f

        // Horizontal lines with increasing spacing (fake perspective)
        var spacing = h * 0.03f
        var y = horizonY
        while (y < h) {
            canvas.drawLine(0f, y, w, y, gridPaint)
            y += spacing
            spacing *= 1.35f
        }

        // Converging vertical lines toward a center vanishing point
        val vanishX = w / 2f
        val count = 9
        for (i in 0..count) {
            val xBottom = w * i / count
            canvas.drawLine(vanishX, horizonY, xBottom, h, gridPaint)
        }
    }
}
