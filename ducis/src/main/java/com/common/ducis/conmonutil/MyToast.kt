package com.common.ducis.conmonutil

import android.content.Context
import android.view.Gravity
import android.widget.Toast

/**
 *@describe：
 *@author：ftt
 *@date：2019/12/2
 */
object MyToast {
    lateinit var context:Context
    fun initContext(context: Context){
        this.context = context
    }

    fun showToast(string: String) {
        var toast = Toast.makeText(context, string, Toast.LENGTH_SHORT)
        toast.setText(string)
        toast.show()
    }

    fun showLongToast(string: String) {
        var toast = Toast.makeText(context, string, Toast.LENGTH_LONG)
        toast.setText(string)
        toast.show()
    }

    fun showToastCenter(string: String) {
        val makeText = Toast.makeText(context, string, Toast.LENGTH_SHORT)
        makeText.setGravity(Gravity.CENTER, 0, 0)
        makeText.setText(string)
        makeText.show()
    }
}
