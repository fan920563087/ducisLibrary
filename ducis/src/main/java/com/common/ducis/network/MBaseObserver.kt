package com.common.ducis.network

import android.accounts.NetworkErrorException
import com.common.ducis.conmonutil.MyLog
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException


/**
 * @ClassName: MBaseObserver
 * @Description:
 * @Author: Fan TaoTao
 * @Date: 2020/6/19
 */
abstract class MBaseObserver<T> : Observer<T> {
    override fun onComplete() {
        MyLog.d("------------******-----------结束请求")
    }

    override fun onSubscribe(d: Disposable?) {
        MyLog.d("-----------******------------开始请求")
    }

    override fun onNext(value: T) {
        MyLog.d("-------请求中--------", String.format("onNext: %s", value.toString()))
        if (value is Response<*>) {
            if (value.isSuccessful) {
                if (value.body() is Int){
                    if (value.body() == 1){
                        onSuccess(value)
                    }else{
                        value.errorBody()?.string()?.let { onFailure(it) }
                    }
                    return
                }
                onSuccess(value)
            } else {
                value.errorBody()?.string()?.let { onFailure(it) }
            }
        } else {
            onSuccess(value)
        }
    }

    override fun onError(e: Throwable) {
        try {
            if (e is ConnectException || e is TimeoutException || e is NetworkErrorException || e is UnknownHostException) {
                MyLog.e("网络错误", "error", e)
                onFailure("网络错误")  //网络错误
            } else if (e is SocketTimeoutException) {
                MyLog.e("连接超时", "error", e)
                onFailure("连接超时")  //网络错误
            } else {
                if (e is HttpException) {
                    val body: ResponseBody? = e.response().errorBody()
                    if(e.code() == 401){
                        onFailureLogout("登录失效，请重新登录")
                    }else{
                        try {
                            var errMessage = body!!.string()
                            MyLog.e("请求错误", errMessage)
                            onFailure(errMessage)
                        } catch (IOe: IOException) {
                            IOe.printStackTrace()
                        }
                    }
                } else {
                    onFailure(e.message.toString())
                }
            }
        } catch (e1: Exception) {
            e1.printStackTrace()
        }
    }


    /**
     * 返回成功
     *
     * @param t 返回对象
     * @throws Exception
     */
    @Throws(Exception::class)
    protected abstract fun onSuccess(t: T)

    /**
     * 返回失败
     *
     * @param e 错误详情
     * @throws Exception
     */
    @Throws(Exception::class)
    protected abstract fun onFailure(e: String)

    /**
     * 登录失效错误
     *
     */
    @Throws(Exception::class)
    protected abstract fun onFailureLogout(value: String)

}