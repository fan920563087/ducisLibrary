package com.common.ducis.network.interceptor

import com.common.ducis.conmonutil.MyLog
import okhttp3.logging.HttpLoggingInterceptor

/**
 * 日志拦截器
 * @author ftt
 * @created  2019/2/27 15:58
 */
object InterceptorUtil {

    //日志拦截器
    fun LogInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> MyLog.i(message) }).setLevel(
            HttpLoggingInterceptor.Level.BODY
        )//设置打印数据的级别
    }
}
