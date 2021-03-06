package com.master.dental.ar.ml.kit.example.helpers

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import com.google.mlkit.vision.face.Face
import com.google.mlkit.vision.face.FaceLandmark.LandmarkType
import kotlin.math.abs
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import com.master.dental.ar.ml.kit.example.R
import java.io.IOException
import android.graphics.Bitmap
import com.google.mlkit.vision.face.FaceContour
import kotlin.math.log


/**
 * Graphic instance for rend    ering face position, contour, and landmarks within the associated
 * graphic overlay view.
 */
private const val TAG = "FaceGraphic"

class FaceGraphic(overlay: GraphicOverlay, private val face: Face, private val context: Context) :
    GraphicOverlay.Graphic(overlay) {
    private val facePositionPaint: Paint
    private val numColors = COLORS.size
    private val idPaints = Array(numColors) { Paint() }
    private val boxPaints = Array(numColors) { Paint() }
    private val labelPaints = Array(numColors) { Paint() }

    init {
        val selectedColor = Color.WHITE
        facePositionPaint = Paint()
        facePositionPaint.color = selectedColor
        for (i in 0 until numColors) {
            idPaints[i] = Paint()
            idPaints[i].color = COLORS[i][0]
            idPaints[i].textSize = ID_TEXT_SIZE
            boxPaints[i] = Paint()
            boxPaints[i].color = COLORS[i][1]
            boxPaints[i].style = Paint.Style.STROKE
            boxPaints[i].strokeWidth = BOX_STROKE_WIDTH
            labelPaints[i] = Paint()
            labelPaints[i].color = COLORS[i][1]
            labelPaints[i].style = Paint.Style.FILL
        }
    }

    /** Draws the face annotations for position on the supplied canvas.  */
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun draw(canvas: Canvas) {
        // Draws a circle at the position of the detected face, with the face's track id below.

        // Draws a circle at the position of the detected face, with the face's track id below.
        val x = translateX(face.boundingBox.centerX().toFloat())
        val y = translateY(face.boundingBox.centerY().toFloat())
//        canvas.drawCircle(
//            x,
//            y,
//            FACE_POSITION_RADIUS,
//            facePositionPaint
//        )

        // Calculate positions.
        val left = x - scale(face.boundingBox.width() / 2.0f)
        val top = y - scale(face.boundingBox.height() / 2.0f)
//        val right = x + scale(face.boundingBox.width() / 2.0f)
//        val bottom = y + scale(face.boundingBox.height() / 2.0f)
//        val lineHeight =
//            ID_TEXT_SIZE + BOX_STROKE_WIDTH
//        var yLabelOffset: Float = if (face.trackingId == null) 0f else -lineHeight

        // Decide color based on face ID
        val colorID =
            if (face.trackingId == null) 0 else abs(face.trackingId!! % NUM_COLORS)

        // Calculate width and height of label box
//        var textWidth = idPaints[colorID].measureText("ID: " + face.trackingId)
//        if (face.smilingProbability != null) {
//            yLabelOffset -= lineHeight
//            textWidth = max(
//                textWidth,
//                idPaints[colorID]
//                    .measureText(String.format(Locale.US, "Happiness: %.2f", face.smilingProbability))
//            )
//        }
//        if (face.leftEyeOpenProbability != null) {
//            yLabelOffset -= lineHeight
//            textWidth = max(
//                textWidth,
//                idPaints[colorID].measureText(
//                    String.format(
//                        Locale.US, "Left eye open: %.2f", face.leftEyeOpenProbability
//                    )
//                )
//            )
//        }
//        if (face.rightEyeOpenProbability != null) {
//            yLabelOffset -= lineHeight
//            textWidth = max(
//                textWidth,
//                idPaints[colorID].measureText(
//                    String.format(
//                        Locale.US, "Right eye open: %.2f", face.rightEyeOpenProbability
//                    )
//                )
//            )
//        }

//        // Draw labels
//        canvas.drawRect(
//            left - BOX_STROKE_WIDTH,
//            top + yLabelOffset,
//            left + textWidth + 2 * BOX_STROKE_WIDTH,
//            top,
//            labelPaints[colorID]
//        )
//        yLabelOffset += ID_TEXT_SIZE
//        canvas.drawRect(left, top, right, bottom, boxPaints[colorID])
//        if (face.trackingId != null) {
//            canvas.drawText(
//                "ID: " + face.trackingId,
//                left,
//                top + yLabelOffset,
//                idPaints[colorID]
//            )
//            yLabelOffset += lineHeight
//        }

        var bitmap: Bitmap? = drawableToBitmap(context.getDrawable(R.drawable.ic_monkey))

//      bitmap=bitmap?.rotate(0)

        val width = scale(face.boundingBox.width() / 1.2f).toInt()
        val height = scale(face.boundingBox.height() / 1.2f).toInt()
        bitmap?.let {
            val imageLeft = x - (width / 2.0f)
            val imageTop = top - (height / 1.5f)
            bitmap = Bitmap.createScaledBitmap(it, width, height, false)
            bitmap?.let { scaledBitMap ->
                canvas.drawBitmap(scaledBitMap, imageLeft, imageTop, idPaints[colorID])
                scaledBitMap.recycle()
            }
            it.recycle()
        }
        bitmap?.recycle()

        // Draws all face contours.
//        for (contour in face.allContours) {
//            if (contour.faceContourType==FaceContour.NOSE_BRIDGE){
//                // get bitmap from assets folder
//
//            }
//            for (point in contour.points) {
//                canvas.drawCircle(
//                    translateX(point.x),
//                    translateY(point.y),
//                    FACE_POSITION_RADIUS,
//                    facePositionPaint
//                )
//            }
//        }

        // Draws smiling and left/right eye open probabilities.
//        if (face.smilingProbability != null) {
//            canvas.drawText(
//                "Smiling: " + String.format(Locale.US, "%.2f", face.smilingProbability),
//                left,
//                top + yLabelOffset,
//                idPaints[colorID]
//            )
//            yLabelOffset += lineHeight
//        }

//        val leftEye = face.getLandmark(FaceLandmark.LEFT_EYE)
//        if (face.leftEyeOpenProbability != null) {
//            canvas.drawText(
//                "Left eye open: " + String.format(Locale.US, "%.2f", face.leftEyeOpenProbability),
//                left,
//                top + yLabelOffset,
//                idPaints[colorID]
//            )
//            yLabelOffset += lineHeight
//        }
//        if (leftEye != null) {
//            Log.e(TAG, "draw: leeeeeeeeeeeeeeeeeeeeeeft")
//            val leftEyeLeft =
//                translateX(leftEye.position.x) - idPaints[colorID].measureText("Left Eye") / 2.0f
//            canvas.drawRect(
//                leftEyeLeft - BOX_STROKE_WIDTH,
//                translateY(leftEye.position.y) + ID_Y_OFFSET - ID_TEXT_SIZE,
//                leftEyeLeft + idPaints[colorID]
//                    .measureText("Left Eye") + BOX_STROKE_WIDTH,
//                translateY(leftEye.position.y) + ID_Y_OFFSET + BOX_STROKE_WIDTH,
//                labelPaints[colorID]
//            )
//            canvas.drawText(
//                "Left Eye",
//                leftEyeLeft,
//                translateY(leftEye.position.y) + ID_Y_OFFSET,
//                idPaints[colorID]
//            )
//        }

//        val rightEye = face.getLandmark(FaceLandmark.RIGHT_EYE)
//        Log.e(TAG, "draw: rrrrrrrrrrrrrrrrright eye post ${rightEye?.position?.x}" )
//        if (face.rightEyeOpenProbability != null) {
//            canvas.drawText(
//                "Right eye open: " + String.format(Locale.US, "%.2f", face.rightEyeOpenProbability),
//                left,
//                top + yLabelOffset,
//                idPaints[colorID]
//            )
//        }
//        if (rightEye != null) {
//            val rightEyeLeft =
//                translateX(rightEye.position.x) - idPaints[colorID].measureText("Right Eye") / 2.0f
//            canvas.drawRect(
//                rightEyeLeft - BOX_STROKE_WIDTH,
//                translateY(rightEye.position.y) + ID_Y_OFFSET - ID_TEXT_SIZE,
//                rightEyeLeft + idPaints[colorID]
//                    .measureText("Right Eye") + BOX_STROKE_WIDTH,
//                translateY(rightEye.position.y) + ID_Y_OFFSET + BOX_STROKE_WIDTH,
//                labelPaints[colorID]
//            )
//            canvas.drawText(
//                "Right Eye",
//                rightEyeLeft,
//                translateY(rightEye.position.y) + ID_Y_OFFSET,
//                idPaints[colorID]
//            )
//        }

//        // Draw facial landmarks
//        drawFaceLandmark(canvas, FaceLandmark.LEFT_EYE)
//        drawFaceLandmark(canvas, FaceLandmark.RIGHT_EYE)
//        drawFaceLandmark(canvas, FaceLandmark.LEFT_CHEEK)
//        drawFaceLandmark(canvas, FaceLandmark.RIGHT_CHEEK)
    }

    private fun drawFaceLandmark(canvas: Canvas, @LandmarkType landmarkType: Int) {
        val faceLandmark = face.getLandmark(landmarkType)
        if (faceLandmark != null) {
            canvas.drawCircle(
                translateX(faceLandmark.position.x),
                translateY(faceLandmark.position.y),
                FACE_POSITION_RADIUS,
                facePositionPaint
            )

        }
    }

    companion object {
        private const val FACE_POSITION_RADIUS = 8.0f
        private const val ID_TEXT_SIZE = 30.0f
        private const val ID_Y_OFFSET = 40.0f
        private const val BOX_STROKE_WIDTH = 5.0f
        private const val NUM_COLORS = 10
        private val COLORS =
            arrayOf(
                intArrayOf(Color.BLACK, Color.WHITE),
                intArrayOf(Color.WHITE, Color.MAGENTA),
                intArrayOf(Color.BLACK, Color.LTGRAY),
                intArrayOf(Color.WHITE, Color.RED),
                intArrayOf(Color.WHITE, Color.BLUE),
                intArrayOf(Color.WHITE, Color.DKGRAY),
                intArrayOf(Color.BLACK, Color.CYAN),
                intArrayOf(Color.BLACK, Color.YELLOW),
                intArrayOf(Color.WHITE, Color.BLACK),
                intArrayOf(Color.BLACK, Color.GREEN)
            )
    }
}

fun drawableToBitmap(mDrawable: Drawable?): Bitmap? {
    if (mDrawable is BitmapDrawable) {
        return mDrawable.bitmap
    }
    mDrawable?.let { drawable ->
        val bitmap =
            Bitmap.createBitmap(
                drawable.intrinsicWidth,
                drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }
    return null
}

fun Context.assetsToBitmap(fileName: String): Bitmap? {
    return try {
        with(assets.open(fileName)) {
            BitmapFactory.decodeStream(this)
        }
    } catch (e: IOException) {
        null
    }
}

infix fun Bitmap.rotate(degrees: Number): Bitmap? {
    return Bitmap.createBitmap(
        this,
        0,
        0,
        width,
        height,
        Matrix().apply { postRotate(degrees.toFloat()) },
        true
    )
}

fun bitmapResizer(bitmap: Bitmap, newWidth: Int, newHeight: Int): Bitmap? {
    val scaledBitmap = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888)
    val ratioX = newWidth / bitmap.width.toFloat()
    val ratioY = newHeight / bitmap.height.toFloat()
    val middleX = newWidth / 2.0f
    val middleY = newHeight / 2.0f
    val scaleMatrix = Matrix()
    scaleMatrix.setScale(ratioX, ratioY, middleX, middleY)
    val canvas = Canvas(scaledBitmap)
    canvas.setMatrix(scaleMatrix)
    canvas.drawBitmap(
        bitmap,
        middleX - bitmap.width / 2,
        middleY - bitmap.height / 2,
        Paint(Paint.FILTER_BITMAP_FLAG)
    )
    return scaledBitmap
}


