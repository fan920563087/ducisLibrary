package com.common.ducis.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.google.gson.Gson
import okhttp3.FormBody
import okhttp3.MediaType
import okhttp3.RequestBody

/**
 * @describe：判断网络是否连接
 * @author：ftt
 * @date：2019/4/24
 */
object MNetWorkUtils {
    fun checkNetworkInfo(context: Context): Boolean {

        val conMan = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val mobileInfo = conMan
            .getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        val wifiInfo = conMan
            .getNetworkInfo(ConnectivityManager.TYPE_WIFI)

        if (mobileInfo == null) {
            val wifiState = wifiInfo.state
            return wifiState == NetworkInfo.State.CONNECTED || wifiState == NetworkInfo.State.CONNECTING
        }

        val mobile = conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
            .state
        val wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
            .state
        return (mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING
                || wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING)
    }


    /**
     * 检查当前网络是否可用
     *
     * @param context
     * @return
     */
    fun isNetworkAvailable(activity: Context): Boolean {
        //得到应用上下文
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）  notificationManager /alarmManager
        val connectivityManager = activity
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // 获取NetworkInfo对象
        val networkInfo = connectivityManager.allNetworkInfo

        if (networkInfo != null && networkInfo.size > 0) {
            for (i in networkInfo.indices) {
                // 判断当前网络状态是否为连接状态
                if (networkInfo[i].state == NetworkInfo.State.CONNECTED) {
                    return true
                }
            }
        }
        return false
    }

    /**
     * 转化请求的body
     * @param any
     * @return RequestBody
     */
    fun getRequestBody(any: Any?): RequestBody {
        var jsonString = Gson().toJson(any)
        var bytes = jsonString.toByteArray(Charsets.UTF_8)
        var bys = StringBuffer()
        for (by in bytes){
            bys.append("$by,")
        }
        var formBody = FormBody.create(MediaType.parse("application/octet-stream"), bytes)
        return formBody
    }

}
