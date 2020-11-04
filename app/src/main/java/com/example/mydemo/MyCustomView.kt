package com.example.mydemo

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import kotlin.math.cos
import kotlin.math.sin

/**
 * @ClassName: MyCustomView
 * @Description:
 * @Author: Fan TaoTao
 * @Date: 2020/8/13
 */
class MyCustomView : View {


    private var mCirclePaint: Paint? = null
    private var mBitmapPaint: Paint? = null
    private var mLinePaint: Paint? = null


    //圆心坐标
    private var cx = 0
    private var cy = 0
    private var insideRadius = 150f
    private var centerRadius = 180f
    private var outRadius = 210f

    //等级线
    private var lineCount = 24
    private var insideX = 0f
    private var insideY = 0f
    private var outX = 0f
    private var outY = 0f


    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    init {
        mCirclePaint = Paint()
        mCirclePaint?.let {
            it.color = Color.parseColor("#ff0000")
            it.strokeWidth = 2f
            it.style = Paint.Style.STROKE
            it.isAntiAlias = true
        }
        mLinePaint = Paint()
        mLinePaint?.let {
            it.color = Color.parseColor("#99000000")
            it.strokeWidth = 1f
            it.isAntiAlias = true
            it.style = Paint.Style.STROKE
        }
        mBitmapPaint = Paint()

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        cx = w / 2
        cy = h / 2

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //画圆
        drawCircle(canvas)

        //画线
        drawLine(canvas)
    }

    private fun drawCircle(canvas: Canvas) {
        canvas.drawCircle(cx.toFloat(), cy.toFloat(), insideRadius, mCirclePaint!!)
        canvas.drawCircle(cx.toFloat(), cy.toFloat(), centerRadius, mCirclePaint!!)
        canvas.drawCircle(cx.toFloat(), cy.toFloat(), outRadius, mCirclePaint!!)
    }

    private fun drawLine(canvas: Canvas) {
        var radius = 360 / lineCount
        var tempRadius = 0
        Log.d("圆心坐标", "cx:$cx,cy:$cy")
        canvas.drawLine(cx.toFloat(), cy - insideRadius, cx.toFloat(), cy - outRadius, mLinePaint!!)
        for (index in 1 until lineCount) {
            tempRadius = radius * index
            insideX = (cx + insideRadius * sin((tempRadius).toDouble())).toFloat()
            insideY = (cy + insideRadius * cos((tempRadius).toDouble())).toFloat()
            outX = (cx + outRadius * sin((tempRadius).toDouble())).toFloat()
            outY = (cy + outRadius * cos((tempRadius).toDouble())).toFloat()


            Log.d(
                "线的坐标",
                "圆心角：${radius * index} | 内圈点: ${insideX.toInt()},${insideY.toInt()} | 外圈点:${outX.toInt()},${outY.toInt()}"
            )
            canvas.drawLine(insideX, insideY, outX, outY, mLinePaint!!)
        }
    }

    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)

    }
}