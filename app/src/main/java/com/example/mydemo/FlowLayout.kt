package com.example.mydemo

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup

/**
 * @ClassName: FlowLayout
 * @Description:
 * @Author: Fan TaoTao
 * @Date: 2020/8/20
 */
class FlowLayout: ViewGroup{

    private var horizontalSpace = 0f
    private var verticalSpace = 0f

    constructor(context:Context): super(context)

    constructor(context: Context,attrs:AttributeSet):super(context, attrs){
        var array = context.obtainStyledAttributes(attrs,R.styleable.flowLayout)
        this.horizontalSpace = array.getDimension(R.styleable.flowLayout_horizontalSpace,0f)
        this.verticalSpace = array.getDimension(R.styleable.flowLayout_verticalSpace,0f)
    }


    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
        TODO("Not yet implemented")
    }
}