package com.common.ducis.baseview


/**
 *@describe：
 *@author：ftt
 *@date：2019/12/10
 */
open class BaseBindPresenter<T : BaseContract.BaseView> : BaseContract.BasePresenter<T> {
    var mView: T? = null
    override fun attachView(view: T) {
        mView = view
    }

    override fun detachView() {
        mView = null
    }
}