package com.common.ducis.network.interceptor

import android.content.Context
import com.common.ducis.conmonutil.MyLog
import com.common.ducis.conmonutil.SharedPreferencesUtils
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * @ClassName: ReceivedCookiesInterceptor
 * @Description:
 * @Author: Fan TaoTao
 * @Date: 2020/10/23
 */
@Suppress("NAME_SHADOWING")
class ReceivedCookiesInterceptor(private val context: Context) : Interceptor {

    var cookieDatas = mutableMapOf<String,String>()
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())
        cookieDatas =
            SharedPreferencesUtils.getHashMapData(context,"cookies",SharedPreferencesUtils.APP_DATA) as HashMap<String, String>
        //这里获取请求返回的cookie
        if (originalResponse.headers("Set-Cookie").isNotEmpty()) {
            var cookieValue = originalResponse.headers("Set-Cookie")
            for (cookie in cookieValue) {
                val cookieArray =
                    cookie.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

                val cookieNames = cookieArray[0].split("=")
                var name = cookieNames[0]
                if (cookieDatas[name].isNullOrEmpty()){
                    cookieDatas[name] = cookieArray[0]
                }
                MyLog.d("存储cookie", String.format("%s=%s",cookieNames[0],cookieNames[1]))
            }
            SharedPreferencesUtils.setHashMapData(
                context, "cookies", cookieDatas, SharedPreferencesUtils.APP_DATA
            )
        }
        return originalResponse
    }
}