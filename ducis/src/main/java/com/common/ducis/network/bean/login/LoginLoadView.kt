package com.common.ducis.network.bean.login

/**
 * @ClassName: LoginLoadView
 * @Description:
 * @Author: Fan TaoTao
 * @Date: 2020/6/19
 */
data class LoginLoadView(var loginFailedNum:Int) {
    var needGvc = 0
    var needPvc = 0
}