package com.common.ducis.baseview

/**
 *@describe：
 *@author：ftt
 *@date：2019/5/9
 */
interface BaseContract {
    interface BaseView
    interface BasePresenter<in T>{
        fun attachView(view: T)
        fun detachView()
    }
}