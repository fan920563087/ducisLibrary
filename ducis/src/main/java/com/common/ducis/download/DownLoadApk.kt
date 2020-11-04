@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "DEPRECATION")

package com.common.ducis.download

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import com.common.ducis.conmonutil.MyLog
import com.common.ducis.conmonutil.MyToast
import com.common.ducis.network.bean.common.CommonVersionView
import com.common.ducis.permission.Acp
import com.common.ducis.permission.AcpListener
import com.common.ducis.permission.AcpOptions
import java.math.BigDecimal

/**
 *@describe：
 *@author：ftt
 *@date：2019/11/28
 */
object DownLoadApk {
    //下载进度监听
    private var progressListener:DownLoadProgressListener? = null
    //apk文件uri
    internal lateinit var apkUri: Uri

    fun downloadApk(
        mContext: Context,
        versionBean: CommonVersionView,
        filename: String
    ) {
        //默认更新
        Acp.getInstance(mContext).request(
            AcpOptions.Builder().setPermissions(
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ).build(),
            object : AcpListener {
                override fun onGranted() {
                    //权限通过开始下载
                    startDown(mContext,url = versionBean.downloadUrl)
                }

                override fun onDenied(permissions: List<String>) {
                    MyToast.showToast(permissions.toString() + "权限拒绝")
                }
            })
    }

    fun startDown(context: Context, url: String){
        DownloadManager.getInstance().download(context,url,object :DownLoadObserver(){
            override fun onComplete() {
                //下载完成
                if(downloadInfo != null){
                    MyLog.d("断点下载","下载详情${downloadInfo?.toString()}")
                    downloadInfo!!.fileUri?.let { apkUri = it }
                    progressListener?.downloadFinish()
                }
            }

            override fun onNext(downloadInfo: DownloadInfo?) {
                super.onNext(downloadInfo)
                MyLog.d("断点下载","最大长度${downloadInfo?.total}")
                MyLog.d("断点下载","当前进度${downloadInfo?.progress}")
                var tempProcess = 0
                downloadInfo?.let {
                    var tempProcess = it.progress.toDouble() / it.total.toDouble()
                    MyLog.d("断点下载","当前进度${tempProcess}")
                    progressListener?.showProgress(tempProcess.toBigDecimal().multiply(BigDecimal(100)).toInt() )
                }

            }
        })
    }

    /**
     * 开始安装
     */
    fun installApk(mContext: Context) {
        val install = Intent(Intent.ACTION_VIEW)
        install.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//判读版本是否在7.0以上
            install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)//添加这一句表示对目标应用临时授权该Uri所代表的文件
            install.setDataAndType(apkUri, "application/vnd.android.package-archive")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                //判读版本是否在8.0以上
                val hasInstallPermission = mContext.packageManager.canRequestPackageInstalls()
                if (!hasInstallPermission) {
                    //注意这个是8.0新API
//                    var packageName = mContext.packageName
//                    val packageURI = Uri.parse("package:$packageName")//设置包名，可直接跳转当前软件的设置页面
//                    val intent = Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI)
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                    mContext.startActivityForResult(intent,GET_INSTALL_PERMISSION)
                }
            }
        } else {
            install.setDataAndType(apkUri, "application/vnd.android.package-archive")
        }
        mContext.startActivity(install)
    }

    fun setLoadLoadProgressListener(listener:DownLoadProgressListener){
        this.progressListener = listener
    }

    interface DownLoadProgressListener{
        /**
         * 下载进度
         */
        fun showProgress(progress:Int)
        /**
         * 下载完成
         */
        fun downloadFinish()
    }

}