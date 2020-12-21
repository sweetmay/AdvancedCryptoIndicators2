package com.sweetmay.advancedcryptoindicators2.view.custom

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import kotlin.properties.Delegates

class SentimentView @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : View(context, attrs, defStyleAttr){

    private var sizeHeight by Delegates.notNull<Int>()
    private var sizeWidth by Delegates.notNull<Int>()

    private val greenPaint: Paint = Paint()
    private val redPaint: Paint = Paint()

    private val greenRectangle = Rect()
    private val redRectangle = Rect()

    init {
        redPaint.color = Color.RED
        greenPaint.color = Color.GREEN
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        sizeWidth = MeasureSpec.getSize(widthMeasureSpec)
        sizeHeight = MeasureSpec.getSize(heightMeasureSpec)
        setMeasuredDimension(sizeWidth, sizeHeight)
    }

    fun setSentiment(value: Int){
        val floatVal = value.toFloat()
        redRectangle.set(0, 0, sizeWidth, sizeHeight)
        val valueAnimator = ValueAnimator.ofFloat(0f, floatVal)
        valueAnimator.duration = 1500
        valueAnimator.start()

        valueAnimator.addUpdateListener {
            greenRectangle.set(0,
                0,
                (sizeWidth*(it.animatedValue as Float)/100).toInt(),
                sizeHeight)
            invalidate()
        }


    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        with(canvas){
            this?.drawRect(redRectangle, redPaint)
            this?.drawRect(greenRectangle, greenPaint)
        }

    }
}