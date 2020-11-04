package com.common.ducis.baseview

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.animation.Animation
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.common.ducis.R
import razerdp.basepopup.BasePopupWindow
import razerdp.util.SimpleAnimationUtils


/**
 *@describe：清理缓存弹窗
 *@author：ftt
 *@date：2020/4/1
 */
class DefaultPopupWindow(context: Context?) : BasePopupWindow(context) {

    var mListener: OnSelectedResultListener? = null
    lateinit var titleTextView:TextView
    lateinit var sureTextView: TextView
    lateinit var cancelTextView: TextView

    override fun onCreateContentView(): View {
        var contentView = createPopupById(R.layout.popup_common)
        titleTextView = contentView.findViewById(R.id.tv_popup_title)
        sureTextView = contentView.findViewById(R.id.tv_popup_sure)
        sureTextView.setOnClickListener {
            mListener!!.onSelected(true)
            dismiss()
        }
        cancelTextView = contentView.findViewById(R.id.tv_popup_cancel)
        cancelTextView.setOnClickListener{
            mListener!!.onSelected(false)
            dismiss()
        }
        return contentView
    }

    override fun onCreateShowAnimation(): Animation {
        return SimpleAnimationUtils.getTranslateVerticalAnimation(1f, 0f,300)
    }

    override fun onCreateDismissAnimation(): Animation {
        return SimpleAnimationUtils.getTranslateVerticalAnimation(0f, 1f,300)
    }

    fun setTitleText(title: String,sureText:String = "确定",cancelText:String = "取消"){
        popupGravity = Gravity.BOTTOM
        titleTextView.text = title
        sureTextView.text = sureText
        cancelTextView.text = cancelText
    }

    fun setTextColor(titleColor:Int ,sureColor:Int,cancelColor:Int){
        titleTextView.setTextColor(ContextCompat.getColor(context,titleColor))
        sureTextView.setTextColor(ContextCompat.getColor(context,sureColor))
        cancelTextView.setTextColor(ContextCompat.getColor(context,cancelColor))
    }

    fun setBackground(backResource:Int){
        contentView.findViewById<RelativeLayout>(R.id.rl_popup).setBackgroundResource(backResource)
    }

    fun setOnClickResultListener(listener: OnSelectedResultListener){
        this.mListener = listener
    }

    interface OnSelectedResultListener {
        fun onSelected(
            isSure: Boolean
        )
    }
}