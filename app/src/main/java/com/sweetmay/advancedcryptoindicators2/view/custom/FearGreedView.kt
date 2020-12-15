package com.sweetmay.advancedcryptoindicators2.view.custom

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.annotation.Dimension
import androidx.annotation.Dimension.DP
import androidx.core.graphics.drawable.toBitmap
import com.sweetmay.advancedcryptoindicators2.R
import kotlin.properties.Delegates

class FearGreedView @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : androidx.appcompat.widget.AppCompatImageView(context, attrs, defStyleAttr) {

    private var sizeHeight by Delegates.notNull<Float>()
    private var sizeWidth by Delegates.notNull<Float>()

    private var newHeight = 0f
    private var newWidth = 0f

    private var oldHeight = 0
    private var oldWidth = 0

    private var currentAngle = 90f

    private val paint: Paint = Paint()
    private val needleOrg: Bitmap
    private val needleMatrix: Matrix
    private val needle: Bitmap

    @Dimension (unit = DP) private var needleHeight = 60
    @Dimension (unit = DP) private var needleWidtht = 15

    init {
        paint.color = Color.BLACK
        paint.isAntiAlias = true

        context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.FearGreedView,
                0,
                0).apply {
            try {
                needleHeight = this.getDimension(R.styleable.FearGreedView_needleHeight, 60f).toInt()
                needleWidtht = this.getDimension(R.styleable.FearGreedView_needleWidth, 15f).toInt()
                needleOrg = this.getDrawable(R.styleable.FearGreedView_needle)?.toBitmap(needleWidtht, needleHeight, null)
                        ?: throw IllegalArgumentException("Provide needle image for the view")
            }finally {
                this.recycle()
            }
        }


        needleMatrix = Matrix()
        needle = Bitmap.createBitmap(needleOrg, 0, 0, needleOrg.width, needleOrg.height, needleMatrix, true)

        oldHeight = needleOrg.height
        oldWidth = needleOrg.width
        needleOrg.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        sizeWidth = MeasureSpec.getSize(widthMeasureSpec).toFloat()
        sizeHeight = MeasureSpec.getSize(heightMeasureSpec).toFloat()
        setMeasuredDimension(sizeWidth.toInt(), sizeHeight.toInt())
        val scaleHeight = (sizeHeight*0.5f)/oldHeight
        val scaleWidth = (sizeWidth*0.03f)/oldWidth
        newHeight = scaleHeight * oldHeight
        newWidth = scaleWidth * oldWidth
        needleMatrix.setScale(scaleWidth, scaleHeight)
        needleMatrix.postTranslate(sizeWidth/2-newWidth+newWidth/2, sizeHeight-newHeight-newWidth)
    }

    fun rotateToAnimation(fng: Int){
        val valueAnimator = ValueAnimator.ofFloat(currentAngle, fng*1.8f)
        valueAnimator.duration = 1500
        valueAnimator.start()

        valueAnimator.addUpdateListener {
            val angleToRotate = it.animatedValue as Float - currentAngle
            needleMatrix.postRotate(angleToRotate, sizeWidth/2, sizeHeight-newWidth)
            currentAngle+= angleToRotate
            invalidate()
        }
    }

    fun rotateTo(fng:Int){
        needleMatrix.postRotate(fng-currentAngle, sizeWidth/2, sizeHeight-newWidth/2)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        with(canvas){
            this?.drawBitmap(needle, needleMatrix, paint)
        }

    }

}