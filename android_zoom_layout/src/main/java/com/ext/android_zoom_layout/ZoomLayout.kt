package com.ext.android_zoom_layout

import android.content.Context
import android.graphics.Canvas
import android.graphics.Matrix
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.widget.FrameLayout
import kotlin.math.max
import kotlin.math.min

class ZoomLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private val zoomMatrix = Matrix()

    private var scaleFactor = 1f
    private var minZoom = 1f
    private var maxZoom = 3f

    private var zoomEnabled = true
    private var scrollEnabled = true
    private var flingEnabled = true

    private var horizontalPanEnabled = true
    private var verticalPanEnabled = true

    private var oneFingerScrollEnabled = true
    private var twoFingersScrollEnabled = true
    private var threeFingersScrollEnabled = true

    private var overScrollHorizontal = true
    private var overScrollVertical = true
    private var overPinchable = true

    private var animationDuration = 280
    private var hasClickableChildren = false
    private var alignment = 0

    private var lastX = 0f
    private var lastY = 0f

    private val scaleDetector =
        ScaleGestureDetector(context, object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
            override fun onScale(detector: ScaleGestureDetector): Boolean {
                if (!zoomEnabled) return false

                val factor = detector.scaleFactor
                val newScale = scaleFactor * factor

                if (!overPinchable) {
                    if (newScale < minZoom || newScale > maxZoom) return false
                }

                scaleFactor = max(minZoom, min(newScale, maxZoom))

                zoomMatrix.postScale(
                    factor,
                    factor,
                    detector.focusX,
                    detector.focusY
                )
                invalidate()
                return true
            }
        })

    init {
        setWillNotDraw(false)

        context.obtainStyledAttributes(attrs, R.styleable.ZoomLayout).apply {
            zoomEnabled = getBoolean(R.styleable.ZoomLayout_zoomEnabled, true)
            scrollEnabled = getBoolean(R.styleable.ZoomLayout_scrollEnabled, true)
            flingEnabled = getBoolean(R.styleable.ZoomLayout_flingEnabled, true)

            horizontalPanEnabled =
                getBoolean(R.styleable.ZoomLayout_horizontalPanEnabled, true)
            verticalPanEnabled =
                getBoolean(R.styleable.ZoomLayout_verticalPanEnabled, true)

            oneFingerScrollEnabled =
                getBoolean(R.styleable.ZoomLayout_oneFingerScrollEnabled, true)
            twoFingersScrollEnabled =
                getBoolean(R.styleable.ZoomLayout_twoFingersScrollEnabled, true)
            threeFingersScrollEnabled =
                getBoolean(R.styleable.ZoomLayout_threeFingersScrollEnabled, true)

            minZoom = getFloat(R.styleable.ZoomLayout_minZoom, 1f)
            maxZoom = getFloat(R.styleable.ZoomLayout_maxZoom, 3f)

            overScrollHorizontal =
                getBoolean(R.styleable.ZoomLayout_overScrollHorizontal, true)
            overScrollVertical =
                getBoolean(R.styleable.ZoomLayout_overScrollVertical, true)
            overPinchable =
                getBoolean(R.styleable.ZoomLayout_overPinchable, true)

            animationDuration =
                getInt(R.styleable.ZoomLayout_animationDuration, 280)

            alignment = getInt(R.styleable.ZoomLayout_alignment, 0)
            hasClickableChildren =
                getBoolean(R.styleable.ZoomLayout_hasClickableChildren, false)

            recycle()
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return !hasClickableChildren
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        scaleDetector.onTouchEvent(event)

        if (!scrollEnabled) return true

        val pointerCount = event.pointerCount
        if (
            (pointerCount == 1 && !oneFingerScrollEnabled) ||
            (pointerCount == 2 && !twoFingersScrollEnabled) ||
            (pointerCount >= 3 && !threeFingersScrollEnabled)
        ) return true

        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                lastX = event.x
                lastY = event.y
            }

            MotionEvent.ACTION_MOVE -> {
                var dx = event.x - lastX
                var dy = event.y - lastY

                if (!horizontalPanEnabled) dx = 0f
                if (!verticalPanEnabled) dy = 0f

                zoomMatrix.postTranslate(dx, dy)
                invalidate()

                lastX = event.x
                lastY = event.y
            }
        }
        return true
    }

    override fun dispatchDraw(canvas: Canvas) {
        canvas.save()
        canvas.concat(zoomMatrix)
        super.dispatchDraw(canvas)
        canvas.restore()
    }
}
