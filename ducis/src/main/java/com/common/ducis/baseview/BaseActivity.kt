package com.common.ducis.baseview

import android.app.Activity
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.common.ducis.conmonutil.MyActivityManager
import com.common.ducis.conmonutil.MyLog
import com.gyf.immersionbar.ImmersionBar


/**
 *@describe：
 *@author：ftt
 *@date：2019/4/26
 */
@Suppress("DEPRECATION")
abstract class BaseActivity : AppCompatActivity() {
    private val Tag = "$javaClass:::"

    private val MIN_DELAY_TIME = 200 // 两次点击间隔不能少于400ms

    private var lastClickTime: Long = 0


    private fun isFastClick(): Boolean {
        var flag = true
        val currentClickTime = System.currentTimeMillis()
        if (currentClickTime - lastClickTime >= MIN_DELAY_TIME) {
            flag = false
        }
        lastClickTime = currentClickTime
        return flag
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ImmersionBar.with(this).statusBarDarkFont(true).init()
        getResources(this)
        setContentView(getLayoutId())
        MyActivityManager.pushActivity(this)
        initView()
        MyLog.d("life", Tag + "onCreate()")
    }

    open fun initView(){
        MyLog.d("life", Tag + "initView()")
    }

    override fun onStart() {
        super.onStart()
        MyLog.d("life", Tag + "onStart()")
    }

    override fun onResume() {
        getResources(this)
        super.onResume()
        MyLog.d("life", Tag + "onResume()")
    }


    override fun onPause() {
        super.onPause()
        MyLog.d("life", Tag + "onPause()")
    }

    override fun onStop() {
        super.onStop()
        MyLog.d("life", Tag + "onPause()")
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            // 判断连续点击事件时间差
            if (isFastClick()) {
                return true
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        //非默认值
        if (newConfig.fontScale != 1f){
            getResources()
        }
        super.onConfigurationChanged(newConfig)
        MyLog.d("life", Tag + "onConfigurationChanged()")
    }

    override fun onRestart() {
        getResources(this)
        super.onRestart()
        MyLog.d("life", Tag + "onRestart()")
    }
    /**
     * 重写getResources()方法，让APP的字体不受系统设置字体大小影响
     */
    open fun getResources(activity: Activity): Resources? {
        val res = activity.resources
        val newConfig = Configuration()
        newConfig.setToDefaults() //设置默认
        res.updateConfiguration(newConfig, res.displayMetrics)
        return res
    }

    override fun onDestroy() {
        super.onDestroy()
        MyActivityManager.popActivity(this)
        MyLog.d("life", Tag + "onDestroy()")
    }

    /**
     * 设置title
     */
    fun setTitle(tv_center: TextView,title:String){
        tv_center.text = title
    }

    /**
     * 无状态返回
     */
    fun back(iv_back:ImageView,resources:Int){
        iv_back.setImageResource(resources)
        iv_back.setOnClickListener{finish()}
    }

    /**
     * 设置错误页面信息
     */
    fun setErrorPageInfo(view:View){
        if (view.visibility == View.GONE){
            view.visibility = View.VISIBLE
        }
    }

    abstract fun getLayoutId(): Int
}