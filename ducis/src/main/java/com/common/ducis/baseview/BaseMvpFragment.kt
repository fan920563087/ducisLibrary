package com.common.ducis.baseview

import android.os.Bundle

/**
 * @ClassName: BaseMvpFragment
 * @Description:
 * @Author: Fan TaoTao
 * @Date: 2020/6/30
 */
@Suppress("UNCHECKED_CAST")
abstract class BaseMvpFragment<V: BaseContract.BaseView,P : BaseContract.BasePresenter<V>>: BaseFragment(){
    var mPresenter: P? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPresenter()
        if (mPresenter != null)
            mPresenter?.attachView(this as V)
    }

    abstract fun initPresenter()

    override fun onDestroy() {
        if (mPresenter != null){
            mPresenter?.detachView()
            mPresenter = null
        }
        super.onDestroy()
    }
}