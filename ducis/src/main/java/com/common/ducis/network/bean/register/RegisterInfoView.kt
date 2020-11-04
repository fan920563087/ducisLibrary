package com.common.ducis.network.bean.register

import java.io.Serializable

/**
 * @ClassName: RegisterInfoView
 * @Description:
 * @Author: Fan TaoTao
 * @Date: 2020/6/28
 */
class RegisterInfoView : Serializable {
    /**
     * 注册来源
     */
    var source: String? = null

    /**
     * 手机号是否已注册
     */
    private var registered = 0

    fun isRegistered(): Boolean {
        return registered == 1
    }

    fun setRegistered(registered: Int) {
        this.registered = registered
    }
}