package com.sweetmay.advancedcryptoindicators2.view.custom

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.annotation.Dimension
import androidx.core.graphics.drawable.toBitmap
import com.sweetmay.advancedcryptoindicators2.R
import kotlin.properties.Delegates

class SentimentView @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : View(context, attrs, defStyleAttr){

    private var sizeHeight by Delegates.notNull<Int>()
    private var sizeWidth by Delegates.notNull<Int>()

    private val greenPaint: Paint = Paint()
    private val redPaint: Paint = Paint()
    private val textPaintDown: Paint = Paint()
    private val textPaintUp: Paint = Paint()

    private val greenRectangle = Rect()
    private val redRectangle = Rect()


    private val upRectangleBounds = Rect()
    private val downRectangleBounds = Rect()
    private val thumbUp: Bitmap
    private val thumbDown: Bitmap

    private var negativePercent: String = ""
    private var positivePercent: String = ""

    private val textOffset = 6f

    @Dimension(unit = Dimension.DP) private var thumbSize: Int

    init {
        context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.SentimentView,
                0,
                0).apply {
            try {
                textPaintUp.color = this.getColor(R.styleable.SentimentView_textColorUpDown, Color.BLACK)
                textPaintDown.color = this.getColor(R.styleable.SentimentView_textColorUpDown, Color.BLACK)
                thumbSize = this.getDimension(R.styleable.SentimentView_thumbSize, 24f).toInt()
                thumbUp = this.getDrawable(R.styleable.SentimentView_thumbUp)?.toBitmap(thumbSize, thumbSize, null)
                        ?: throw IllegalArgumentException("Provide thumb up image for the view")
                thumbDown = this.getDrawable(R.styleable.SentimentView_thumbDown)?.toBitmap(thumbSize, thumbSize, null)
                        ?: throw IllegalArgumentException("Provide thumb down image for the view")
            }finally {
                this.recycle()
            }
        }
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
        positivePercent = (value).toString() + "%"
        negativePercent = (100-value).toString() + "%"

        textPaintUp.textSize = thumbSize.toFloat()*.75f
        textPaintDown.textSize = thumbSize.toFloat()*.75f

        textPaintUp.getTextBounds(positivePercent, 0, positivePercent.length, upRectangleBounds)
        textPaintDown.getTextBounds(negativePercent, 0, negativePercent.length, downRectangleBounds)

        val floatVal = value.toFloat()
        redRectangle.set(0, 0, sizeWidth, (sizeHeight-thumbSize*1.5f).toInt())
        greenRectangle.set(0, 0, (sizeWidth*(floatVal)/100).toInt(),
            (sizeHeight-thumbSize*1.5f).toInt())
    }

    fun setSentimentAnim(value: Int) {
        positivePercent = (value).toString() + "%"
        negativePercent = (100-value).toString() + "%"

        textPaintUp.textSize = thumbSize.toFloat()*.75f
        textPaintDown.textSize = thumbSize.toFloat()*.75f

        textPaintUp.getTextBounds(positivePercent, 0, positivePercent.length, upRectangleBounds)
        textPaintDown.getTextBounds(negativePercent, 0, negativePercent.length, downRectangleBounds)

        val floatVal = value.toFloat()
        redRectangle.set(0, 0, sizeWidth, (sizeHeight-thumbSize*1.5f).toInt())
        val valueAnimator = ValueAnimator.ofFloat(0f, floatVal)
        valueAnimator.duration = 1500
        valueAnimator.start()

        valueAnimator.addUpdateListener {

            greenRectangle.set(0,
                0,
                (sizeWidth*(it.animatedValue as Float)/100).toInt(),
                (sizeHeight-thumbSize*1.5f).toInt())

            invalidate()
        }
    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            with(it){
                drawBitmap(thumbUp, 0f, (sizeHeight-thumbSize).toFloat(), null)
                drawBitmap(thumbDown, (sizeWidth-thumbSize).toFloat(), (sizeHeight-thumbSize).toFloat(), null)

                drawText(positivePercent, thumbSize.toFloat()+textOffset, sizeHeight.toFloat()-upRectangleBounds.height()/2, textPaintUp)
                drawText(negativePercent, (sizeWidth-thumbSize-downRectangleBounds.width()-textOffset), sizeHeight.toFloat()-downRectangleBounds.height()/2, textPaintDown)

                drawRect(redRectangle, redPaint)
                drawRect(greenRectangle, greenPaint)
            }
        }
    }


}