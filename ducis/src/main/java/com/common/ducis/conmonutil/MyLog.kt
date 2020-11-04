package com.common.ducis.conmonutil

import android.util.Log


/**
 * @describe：自定义LOG打印工具
 * @author：ftt
 * @date：2019/2/28
 */
object MyLog {
    var OPENLOG = false

    fun d(tag: String, msg: String) {
        if (OPENLOG)
            Log.i(tag, msg)
    }

    fun d(msg: String) {
        if (OPENLOG)
            Log.d("ducis", msg)
    }

    fun e(msg: String) {
        if (OPENLOG)
            Log.e("ducis", msg)
    }

    fun i(msg: String) {
        if (OPENLOG)
            Log.i("ducis", msg)
    }

    fun w(msg: String) {
        if (OPENLOG)
            Log.w("ducis", msg)
    }

    fun debug(tag: String, msg: String) {
        if (OPENLOG) {
            Log.d(tag, msg)
        }

    }

    fun error(tag: String, msg: String) {
        if (OPENLOG) {
            Log.e(tag, msg)
        }

    }

    fun e(tag: String, msg: String, e: Throwable) {
        if (OPENLOG) {
            Log.e(tag, msg, e)
            e.printStackTrace()
        }

    }

    fun v(tag: String, msg: String) {
        if (OPENLOG) {
            Log.v(tag, msg)
        }

    }

    fun i(tag: String, msg: String?) {
        if (OPENLOG) {
            if (msg.isNullOrEmpty()) {
                Log.i(tag, "")
            } else {
                Log.i(tag, msg)
            }
        }
    }

    fun e(tag: String, msg: String) {
        if (OPENLOG) {
            Log.e(tag, msg)
        }
    }

    fun w(tag: String, msg: String) {
        if (OPENLOG) {
            Log.w(tag, msg)
        }
    }

    fun d(tag: String, scrollY: Int) {
        if (OPENLOG) {
            Log.d(tag, scrollY.toString() + "")
        }
    }

    fun d(e: Exception) {
        if (OPENLOG) {
            e.printStackTrace()
        }
    }

    fun w(e: Throwable) {
        if (OPENLOG) {
            e.printStackTrace()
        }
    }
}
