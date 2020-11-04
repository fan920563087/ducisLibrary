package com.common.ducis.network.interceptor

import android.content.Context
import com.common.ducis.conmonutil.SharedPreferencesUtils
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * @ClassName: AddCookiesInterceptor
 * @Description:
 * @Author: Fan TaoTao
 * @Date: 2020/10/23
 */
class AddCookiesInterceptor(private val context: Context) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val cookies = SharedPreferencesUtils.getHashMapData(
            context,
            "cookies",
            SharedPreferencesUtils.APP_DATA
        )

        var cookieBuffer = StringBuffer()
        if (cookies.isNotEmpty()) {
            for (cookie in cookies) {
                cookieBuffer.append(cookie.value+";")
            }
        }
        if(cookieBuffer.length > 1){
            builder.addHeader("cookie", cookieBuffer.substring(0,cookieBuffer.length-1))
        }
        builder.addHeader("m-request-type", "mrpc")
        builder.addHeader("mrpc-serializer-format", "JSON")
        builder.addHeader("Content-Type", "application/octet-stream")
//        builder.addHeader("m-request-profile", "Miles-Local")
        builder.addHeader("m-request-profile", "product")
        return chain.proceed(builder.build())
    }
}
