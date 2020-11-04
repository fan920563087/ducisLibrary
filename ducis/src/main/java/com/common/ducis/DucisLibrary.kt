package com.common.ducis

import android.content.Context
import com.common.ducis.conmonutil.MyLog
import com.common.ducis.conmonutil.MyToast
import com.common.ducis.exception.CrashCatchHandler

/**
 * @ClassName: DucisLibrary
 * @Description:
 * @Author: Fan TaoTao
 * @Date: 2020/11/3
 */
object DucisLibrary {
    fun init(context: Context,isOpenLog:Boolean){
        MyToast.initContext(context)
        MyLog.OPENLOG = isOpenLog
        CrashCatchHandler.instance.init(context)
    }
}