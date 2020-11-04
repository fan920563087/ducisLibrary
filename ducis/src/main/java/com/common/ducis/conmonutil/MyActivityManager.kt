package com.common.ducis.conmonutil

import android.app.Activity
import java.util.*

object MyActivityManager {

    private var activityStack: Stack<Activity>? = null

    // 指定退出几个页面
    fun popActivityCount(count: Int) {
        var index = 1
        while (index <= count) {
            val activity = currentActivity() ?: break
            popActivity(activity)
            index++
        }
    }

    // 退出栈顶Activity
    fun popActivity(activity: Activity?) {
        if (activity != null) { // 在从自定义集合中取出当前Activity时，也进行了Activity的关闭操作
            activity.finish()
            activityStack!!.remove(activity)
        }
    }

    // 获得当前栈顶Activity
    fun currentActivity(): Activity? {
        var activity: Activity? = null
        return if (activityStack == null) {
            null
        } else {
            if (!activityStack!!.empty()) activity =
                activityStack!!.lastElement()
            activity
        }
    }

    // 获得当前栈顶Activity
    fun currentPreActivity(): Activity? {
        var activity: Activity
        return if (activityStack == null) {
            null
        } else {
            if (!activityStack!!.empty() && activityStack!!.size > 2) {
                activity =
                    activityStack!![activityStack!!.size - 2]
                activity
            } else {
                null
            }
        }
    }

    // 将当前Activity推入栈中
    fun pushActivity(activity: Activity) {
        if (activityStack == null) {
            activityStack = Stack()
        }
        activityStack!!.add(activity)
    }

    // 退出栈中所有Activity
    fun popAllActivity() {
        while (true) {
            val activity = currentActivity() ?: break
            popActivity(activity)
        }
    }

    // 退出栈中除cls外所有Activity
    fun popAllActivityExceptOne(cls: Class<*>) {
        while (true) {
            val activity = currentActivity() ?: break
            if (activity.javaClass == cls) {
                break
            }
            popActivity(activity)
        }
    }
}