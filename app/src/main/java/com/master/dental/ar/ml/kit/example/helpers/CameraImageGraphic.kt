package com.master.dental.ar.ml.kit.example.helpers

import android.graphics.Bitmap
import android.graphics.Canvas
import com.master.dental.ar.ml.kit.example.helpers.GraphicOverlay.Graphic


/** Draw camera image to background.  */
class CameraImageGraphic(overlay: GraphicOverlay?, private val bitmap: Bitmap) : Graphic(
    overlay!!
) {

    override fun draw(canvas: Canvas) {

        canvas.drawBitmap(bitmap, getTransformationMatrix(), null)
    }

}


/**
 * (-1, 1) ------- (1, 1)
 * |    \           |
 * |       \        |
 * |          \     |
 * |             \  |
 * (-1, -1) ------ (1, -1)
 * Ensure triangles are front-facing, to support glCullFace().
 * This quad will be drawn using GL_TRIANGLE_STRIP which draws two
 * triangles: v0->v1->v2, then v2->v1->v3.
 */
private val QUAD_COORDS = floatArrayOf(
    -1.0f, -1.0f, +1.0f, -1.0f, -1.0f, +1.0f, +1.0f, +1.0f
)