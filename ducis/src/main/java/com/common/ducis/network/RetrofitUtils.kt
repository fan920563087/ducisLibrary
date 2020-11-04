package com.common.ducis.network

import android.content.Context
import com.common.ducis.network.interceptor.AddCookiesInterceptor
import com.common.ducis.network.interceptor.InterceptorUtil
import com.common.ducis.network.interceptor.NullOnEmptyConverterFactory
import com.common.ducis.network.interceptor.ReceivedCookiesInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * retrofit工具类
 * @author ftt
 * @created ad 2019/2/27 14:25
 */
object RetrofitUtils {
    private var mRetrofit:Retrofit? = null

    private val TIMEOUT = 6//超时时间

    var mOkHttpClient: OkHttpClient.Builder? = null
    lateinit var mContext:Context
    /**
     * 初始化retrofit
     */
    fun initRetrofit(baseUrl: String,context:Context): Retrofit {
        mContext = context
        if (mRetrofit == null) {
            mRetrofit = Retrofit.Builder()
                .client(initOKHttp().build())
                //设置域名
                .baseUrl(baseUrl)
                //设置解析转换工厂
                .addConverterFactory(NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            return mRetrofit!!
        }
        return mRetrofit!!
    }

    /**
     * 全局httpclient
     *
     * @return
     */
    open fun initOKHttp(): OkHttpClient.Builder {
        if (mOkHttpClient == null) mOkHttpClient = OkHttpClient.Builder()
        mOkHttpClient!!.interceptors().add(AddCookiesInterceptor(mContext))//设置请求头
        mOkHttpClient!!.interceptors().add(ReceivedCookiesInterceptor(mContext))//设置请求头
        mOkHttpClient!!.interceptors().add(InterceptorUtil.LogInterceptor())//设置日志拦截
        mOkHttpClient!!.connectTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)//设置连接超时时间
        mOkHttpClient!!.readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)//设置读取超时时间
        mOkHttpClient!!.writeTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)//设置写入超时时间
        //设置cookie
//        mOkHttpClient!!.cookieJar(PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(mContext)))
        return mOkHttpClient!!
    }

}
