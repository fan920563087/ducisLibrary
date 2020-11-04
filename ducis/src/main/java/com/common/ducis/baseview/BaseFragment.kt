package com.common.ducis.baseview

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.common.ducis.conmonutil.MyLog

/**
 *@describe：
 *@author：ftt
 *@date：2019/6/5
 */
abstract class BaseFragment : Fragment() {
    private val FTAG = "$javaClass:::"
    lateinit var mActivity: BaseActivity

    abstract fun getLayoutId(): Int

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as BaseActivity
        getResources(mActivity)
        MyLog.d("life", FTAG + "onAttach")
    }

    open fun initView(){
        MyLog.d("life", FTAG + "initView()")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyLog.d("life", FTAG + "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        MyLog.d("life", FTAG + "onCreateView")
        val rootView = FrameLayout(mActivity)
        val view = inflater.inflate(getLayoutId(), rootView, false)
        rootView.addView(view)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MyLog.d("life", FTAG + "onViewCreated")
        initView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getResources(mActivity)
        MyLog.d("life", FTAG + "onActivityCreated")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        MyLog.d("life", FTAG + "onSaveInstanceState")
    }

    override fun onStart() {
        super.onStart()
        MyLog.d("life", FTAG + "onStart")
    }

    override fun onStop() {
        super.onStop()
        MyLog.d("life", FTAG + "onStop")
    }

    override fun onResume() {
        getResources(mActivity)
        super.onResume()
        MyLog.d("life", FTAG + "onResume")
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        MyLog.d("life", FTAG + "onHiddenChanged:" + hidden)
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

    open fun onBack(): Boolean {
        return false
    }

    /**
     * 刷新数据
     */
    open fun onRefreshData(){

    }

    /**
     * 切换币对专用刷新
     */
    open fun onRefreshDataByKey(key:String){

    }

}