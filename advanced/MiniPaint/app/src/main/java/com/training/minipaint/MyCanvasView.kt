package com.training.minipaint

import android.content.Context
import android.graphics.*
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import androidx.core.content.res.ResourcesCompat

private const val STROKE_WIDTH = 12f // has to be float

class MyCanvasView(context: Context): View(context) {

    // canvas and bitmap to cache the drawings
    private lateinit var extraCanvas: Canvas
    private lateinit var extraBitmap: Bitmap

    // initial the colors
    private val backgroundColor = ResourcesCompat.getColor(resources, R.color.colorBackground, null)
    private val drawColor = ResourcesCompat.getColor(resources, R.color.colorPaint, null)

    // Set up the paint with which to draw.
    private val paint = Paint().apply {
        color = drawColor
        // Smooths out edges of what is drawn without affecting shape.
        isAntiAlias = true
        // Dithering affects how colors with higher-precision than the device are down-sampled.
        isDither = true
        style = Paint.Style.STROKE // default: FILL
        strokeJoin = Paint.Join.ROUND // default: MITER
        strokeCap = Paint.Cap.ROUND // default: BUTT
        strokeWidth = STROKE_WIDTH // default: Hairline-width (really thin)
    }
    // store the user touch path in native path object
    private var path = Path()
    // track user touch
    private var motionTouchEventX = 0f
    private var motionTouchEventY = 0f
    // cache latest x and y values - after the touch up these the next start for touch down
    private var currentX = 0f
    private var currentY = 0f
    // with path we can intolerate the path between points (instead of drawing each pixel)
    private val touchTolerance = ViewConfiguration.get(context).scaledTouchSlop
    // rectangle object to draw a frame
    private lateinit var frame: Rect


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        // recycle the bitmap before creating new one to avoid memory leaks
        if (::extraBitmap.isInitialized) extraBitmap.recycle()
        // create new instances with the screen size
        extraBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        extraCanvas = Canvas(extraBitmap)
        extraCanvas.drawColor(backgroundColor)

        // Calculate a rectangular frame around the picture.
        val padding = 40
        frame = Rect(padding, padding, width - padding, height - padding)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        // draw the cashed bitmap
        canvas?.drawBitmap(extraBitmap, 0f, 0f, null)
        // Draw a frame around the canvas.
        canvas?.drawRect(frame, paint)
    }

    private fun touchStart() {
        path.reset()
        path.moveTo(motionTouchEventX, motionTouchEventY)
        currentX = motionTouchEventX
        currentY = motionTouchEventY
    }

    private fun touchMove() {
        // calculate travel distance
        val dx = Math.abs(motionTouchEventX - currentX)
        val dy = Math.abs(motionTouchEventY - currentY)

        // create curve between the points
        if (dx >= touchTolerance || dy >= touchTolerance) {
            // QuadTo() adds a quadratic bezier from the last point,
            // approaching control point (x1,y1), and ending at (x2,y2).
            // quadTo draw smoother corners than lineTo
            path.quadTo(currentX, currentY, (motionTouchEventX + currentX) / 2, (motionTouchEventY + currentY) / 2)
            currentX = motionTouchEventX
            currentY = motionTouchEventY
            // Draw the path in the extra bitmap to cache it.
            extraCanvas.drawPath(path, paint)
        }
        // refresh the drawing - will call onDraw()
        invalidate()
    }

    private fun touchUp() {
        // Reset the path so it doesn't get drawn again.
        path.reset()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let {
            motionTouchEventX = event.x
            motionTouchEventY = event.y
            when (event.action) {
                MotionEvent.ACTION_DOWN -> touchStart()
                MotionEvent.ACTION_MOVE -> touchMove()
                MotionEvent.ACTION_UP -> touchUp()
            }
        }

        return true
    }
}