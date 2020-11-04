package com.common.ducis.network.bean.common

/**
 * @ClassName: CommonVersionView
 * @Description:
 * @Author: Fan TaoTao
 * @Date: 2020/10/22
 */
data class CommonVersionView(val versionId:Long,val appVersionId:Long,val downloadUrl:String) {

    /**
     * 版本描述
     */
    val versionDescribe: String? = null

    /**
     * 版本名称
     */
    val versionName: String? = null

    /**
     * 创建日期
     */
    val createDate: Long = 0
}