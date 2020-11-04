@file:Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS",
    "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS"
)

package com.common.ducis.conmonutil

import android.content.Context
import android.content.SharedPreferences
import org.json.JSONException
import org.json.JSONArray
import org.json.JSONObject
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


/**
 * @describe：覆盖模式的SharePreference
 * @author：ftt
 * @date：2019/2/28
 */
object SharedPreferencesUtils {

    val APP_DATA = "app_data_save"
    val USER_DATA = "user_data_save"
    val IDENTIFICATION = "IDENTIFICATION"
    val LOGIN_STATE = "LOGIN_STATE"

    private var mPreferences: SharedPreferences? = null        // SharedPreferences的实例

    fun getSp(context: Context, spName: String): SharedPreferences {
        if (mPreferences == null) {
            mPreferences = context.getSharedPreferences(spName, Context.MODE_PRIVATE)
        }
        return mPreferences!!
    }

    fun setBool(context: Context, key: String, b: Boolean, fileName: String) {
        val sp = getSp(context, fileName)
        val ed = sp.edit()
        ed.putBoolean(key, b)
        ed.commit()
    }

    fun getBool(context: Context, key: String, fileName: String): Boolean {
        val sp = getSp(context, fileName)
        return sp.getBoolean(key, false)
    }

    fun getBool(
        context: Context, key: String,
        defaultValue: Boolean, fileName: String
    ): Boolean {
        val sp = getSp(context, fileName)
        return sp.getBoolean(key, defaultValue)
    }

    fun setString(context: Context, key: String, b: String, fileName: String) {
        val sp = getSp(context, fileName)
        val ed = sp.edit()
        ed.putString(key, b)
        ed.commit()
    }

    fun getString(context: Context, key: String, fileName: String): String? {
        val sp = getSp(context, fileName)
        return sp.getString(key, "")
    }

    fun setInt(context: Context, key: String, b: Int, fileName: String) {
        val sp = getSp(context, fileName)
        val ed = sp.edit()
        ed.putInt(key, b)
        ed.commit()
    }


    fun getInt(context: Context, key: String, defaultVal: Int, fileName: String): Int {
        val sp = getSp(context, fileName)
        return sp.getInt(key, defaultVal)
    }

    fun setLong(context: Context, key: String, b: Long, fileName: String) {
        val sp = getSp(context, fileName)
        val ed = sp.edit()
        ed.putLong(key, b)
        ed.commit()
    }

    fun getLong(context: Context, key: String, defaultVal: Long, fileName: String): Long {
        val sp = getSp(context, fileName)
        return sp.getLong(key, defaultVal)
    }

    fun getLong(context: Context, key: String, fileName: String): Long {
        return getLong(context, key, 0, fileName)
    }

    fun setFloat(context: Context, key: String, b: Float, fileName: String) {
        val sp = getSp(context, fileName)
        val ed = sp.edit()
        ed.putFloat(key, b)
        ed.commit()
    }

    fun getFloat(context: Context, key: String, fileName: String): Float {
        return getFloat(context, key, 0.0f, fileName)
    }

    fun getFloat(context: Context, key: String, defaultVal: Float, fileName: String): Float {
        val sp = getSp(context, fileName)
        return sp.getFloat(key, defaultVal)
    }

    fun setStringSet(context: Context, key: String, set: Set<String>, fileName: String) {
        val sp = getSp(context, fileName)
        val ed = sp.edit()
        ed.putStringSet(key, set)
        ed.commit()
    }

    fun getStringSet(context: Context, key: String, fileName: String): Set<String>? {
        val sp = getSp(context, fileName)
        return sp.getStringSet(key, null)
    }

    fun setStringList(context: Context, key: String, datas: List<String>?, fileName:String) {
        val gson = Gson()
        val jsonStr = gson.toJson(datas) //将List转换成Json
        val sp = getSp(context,fileName)
        val editor = sp.edit()
        editor.putString(key, jsonStr)
        editor.commit()
    }

    fun getStringList(context: Context, key: String, fileName: String): List<String> {
        val sp = getSp(context, fileName)
        var peopleListJson = sp.getString(key,"") //取出key的值，如果值为空，则将第二个参数作为默认值赋值
        var datas: List<String> = emptyArray<String>().toList()
        if(peopleListJson!!.isNotEmpty())  //防空判断
        {
            val gson = Gson()
            datas = gson.fromJson(peopleListJson, object : TypeToken<List<String>>() {}.type) //将json字符串转换成List集合
        }
        return datas
    }

    fun setHashMapData(context: Context, key: String, datas: Map<String, String>?, fileName:String) {
        val mJsonArray = JSONArray()
        val iterator = datas!!.entries.iterator()

        val `object` = JSONObject()

        while (iterator.hasNext()) {
            val entry = iterator.next()
            try {
                `object`.put(entry.key, entry.value)
            } catch (e: JSONException) {
            }
        }
        mJsonArray.put(`object`)
        val sp = getSp(context,fileName)
        val editor = sp.edit()
        editor.putString(key, mJsonArray.toString())
        editor.commit()
    }

    fun getHashMapData(context: Context, key: String, fileName: String): Map<String, String> {
        val datas = HashMap<String,String>()
        val sp = getSp(context,fileName)
        val result = sp.getString(key, "")
        try {
            val array = JSONArray(result)
            for (i in 0 until array.length()) {
                val itemObject = array.getJSONObject(i)
                val names = itemObject.names()
                if (names != null) {
                    for (j in 0 until names.length()) {
                        val name = names.getString(j)
                        val value = itemObject.getString(name)
                        datas.put(name, value)
                    }
                }
            }
        } catch (e: JSONException) {
            return datas
        }

        return datas
    }

    /**
     * 登录
     */
    fun login(context:Context,content:String){
        val sp = getSp(context, USER_DATA)
        val ed = sp.edit()
        ed.putString(IDENTIFICATION, content)
        ed.putBoolean(LOGIN_STATE,true)
        ed.commit()
    }

    /**
     * 退出登录
     */
    fun logout(context:Context){
        val sp = getSp(context, USER_DATA)
        val ed = sp.edit()
        ed.putBoolean(LOGIN_STATE,false)
        ed.commit()
    }

    /**
     * 获取登录状态
     */
    fun isLogin(context: Context):Boolean{
        val sp = getSp(context, USER_DATA)
        return sp.getBoolean(LOGIN_STATE, false)
    }

    /**
     * 获取登录唯一标示
     */
    fun getLogin(context: Context):String{
        val sp = getSp(context, USER_DATA)
        return sp.getString(IDENTIFICATION,"")!!
    }

    fun clearSP(context: Context, fileName: String) {
        val sp = getSp(context, fileName)
        val ed = sp.edit()
        ed.clear()
        ed.apply()
    }

    @JvmStatic
    fun getString(any: Any, authId: String, appData: String) {

    }
}
