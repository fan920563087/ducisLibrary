package com.common.ducis.baseview


/**
 *@describe：
 *@author：ftt
 *@date：2019/12/10
 */
@Suppress("UNCHECKED_CAST")
abstract class BaseMvpActivity<V: BaseContract.BaseView,P : BaseContract.BasePresenter<V>> : BaseActivity(){
    var mPresenter: P? = null

    override fun initView() {
        initPresenter()
        if (mPresenter != null)
            mPresenter?.attachView(this as V)
        super.initView()
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