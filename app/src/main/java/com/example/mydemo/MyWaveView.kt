package com.example.mydemo

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import kotlin.math.roundToInt

/**
 * @ClassName: MyWaveView
 * @Description:
 * @Author: Fan TaoTao
 * @Date: 2020/8/3
 */
class MyWaveView:View, View.OnClickListener {

    private lateinit var mPath: Path

    private var mPaintWave:Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var mWaveLength = 0f
    private var mScreenWidth = 0
    private var mScreenHeight = 0
    private var mCenterY = 0f
    private var mWaveCount = 0

    //动画差值器
    private lateinit var mValueAnimator:ValueAnimator
    //波浪流动X轴偏移量
    private var mOffsetX = 0f
    //波浪流动Y轴偏移量
    private var mOffsetY = 0f
    private var count = 0

    constructor(context: Context):super(context)

    constructor(context:Context,attrs:AttributeSet):super(context, attrs){
        mPaintWave.color = Color.LTGRAY
        mPaintWave.strokeWidth = 8F
        mPaintWave.style = Paint.Style.STROKE

        mWaveLength = 800f
    }

    constructor(context: Context,attrs: AttributeSet,defStyleAttr:Int):super(context, attrs, defStyleAttr){

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mPath = Path()
        setOnClickListener(this)

        mScreenWidth = w
        mScreenHeight = h
        mCenterY = (h/2).toFloat()

        mWaveCount = (mScreenWidth / mWaveLength + 1.5).roundToInt()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mPath.reset()
        mPath.moveTo(-mWaveLength + mOffsetX,mCenterY)

        for (index in 0..mWaveCount){
            mPath.rQuadTo(mWaveLength / 4, -60f, mWaveLength / 2, 0f)
            mPath.rQuadTo(mWaveLength / 4, +60f, mWaveLength / 2, 0f)
        }
        mPath.lineTo(mScreenWidth.toFloat(), mScreenHeight.toFloat())
        mPath.lineTo(0f, mScreenHeight.toFloat())
        mPath.close()
        canvas?.drawPath(mPath,mPaintWave)
    }

    override fun onClick(view: View) {
        //动画运动距离
        mValueAnimator = ValueAnimator.ofInt(0, mWaveLength.toInt())
        //动画间隔时间
        mValueAnimator.duration = 100
        //循环次数
        mValueAnimator.repeatCount = ValueAnimator.INFINITE
        //设置线性运动差值器
        mValueAnimator.interpolator = LinearInterpolator()
        mValueAnimator.addUpdateListener {
            mOffsetX = it.animatedValue as Float
            count++
            invalidate()
        }
        mValueAnimator.start()
    }

}