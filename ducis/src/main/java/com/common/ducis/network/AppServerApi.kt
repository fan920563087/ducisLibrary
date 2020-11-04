package com.common.ducis.network

import com.common.ducis.network.bean.account.AccountBasicInfoView
import com.common.ducis.network.bean.account.AccountSecurityInfoView
import com.common.ducis.network.bean.common.CommonStatusView
import com.common.ducis.network.bean.common.CommonTopMenuView
import com.common.ducis.network.bean.common.CommonVersionView
import com.common.ducis.network.bean.login.LoginLoadView
import com.common.ducis.network.bean.register.RegisterInfoView
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Url

/**
 * @ClassName: AppServerApi
 * @Description:
 * @Author: Fan TaoTao
 * @Date: 2020/6/18
 */
interface AppServerApi {


    //---------------------------------------------通用模块----------------------------------------------------------------------
    /**
     * 上报本机信息并查询登录状态
     * @method POST
     * @param body
     * @return view  MobileAndroidStatusView
     */
    @POST
    fun status(
        @Url url: String,
        @Body body: RequestBody
    ): Observable<CommonStatusView>

    /**
     * 获取当前最新已发布版本信息
     * @method POST
     * @return view  MobileAndroidVersionView
     */
    @POST
    fun version(
        @Url url: String,
        @Body body: RequestBody
    ): Observable<CommonVersionView>

    /**
     * 将APP本地异常上报给服务器
     * @method POST
     * @param body
     * @return view int
     */
    @POST
    fun logException(
        @Url url: String,
        @Body body: RequestBody
    ): Observable<Response<Int>>

    /**
     * 获取App顶层导航（下方导航栏）
     * @method POST
     * @return view MobileAndroidTopMenuView
     */
    @POST
    fun topMenu(
        @Url url: String,
        @Body body: RequestBody
    ): Observable<CommonTopMenuView>

    //---------------------------------------------注册模块----------------------------------------------------------------------
    /**
     * 验证是否注册过
     * @method POST
     * @return view RpcAccountRegisterInfoView
     */
    @POST
    fun isRegistered(
        @Url url: String,
        @Body body: RequestBody
    ): Observable<RegisterInfoView>

    /**
     * 获取注册页面信息
     * @method POST
     * @return view RpcAccountRegisterInfoView
     */
//    @POST
//    fun registerLoad(
//        @Url url: String,
//        @Body body: RequestBody
//    ): Observable<RpcRegisterLoadParam>

    /**
     * 发送注册手机号验证码
     * @method POST
     * @return view int
     */
    @POST
    fun sendRegisterPvc(
        @Url url: String,
        @Body body: RequestBody
    ): Observable<Response<Int>>

    /**
     * 注册，仅提供手机号和验证码，不设置密码
     * @method POST
     * @return view int
     */
    @POST
    fun registerCommit(
        @Url url: String,
        @Body body: RequestBody
    ): Observable<Response<Int>>

    /**
     * 注册时，设置登录密码
     * @method POST
     * @return view int
     */
    @POST
    fun registerSetPassword(
        @Url url: String,
        @Body body: RequestBody
    ): Observable<Response<Int>>

    //---------------------------------------------登录模块----------------------------------------------------------------------

    /**
     * 获取使用密码登录的页面数据
     * @method POST
     * @return view LoginLoadView
     */
    @POST
    fun passwordLoginLoad(
        @Url url: String
    ): Observable<LoginLoadView>

    /**
     * 发送登录验证码
     * @method POST
     * @return view int
     */
    @POST
    fun sendPvcForLogin(
        @Url url: String,
        @Body body: RequestBody
    ): Observable<Response<Int>>

    /**
     * 使用密码登录，可能需要提供验证码（如果连续密码错误）
     * @method POST
     * @return view Int
     */
    @POST
    fun passwordLoginCommit(
        @Url url: String,
        @Body body: RequestBody
    ): Observable<Response<Int>>

    /**
     * 退出登录
     * @method POST
     * @return view int
     */
    @POST
    fun logout(
        @Url url: String
    ): Observable<Response<Int>>

    //---------------------------------------------密码模块----------------------------------------------------------------------

    /**
     * 发送重置登录密码的验证码
     * @method POST
     * @return view int
     */
    @POST
    fun sendResetPasswordPvc(
        @Url url: String,
        @Body body: RequestBody
    ): Observable<Response<Int>>

    /**
     * 验证重置登录密码的验证码
     * @method POST
     * @return view int
     */
    @POST
    fun validResetPasswordPvc(
        @Url url: String,
        @Body body: RequestBody
    ): Observable<Response<Int>>

    /**
     * 重置登录密码
     * @method POST
     * @return view int
     */
    @POST
    fun resetPasswordCommit(
        @Url url: String,
        @Body body: RequestBody
    ): Observable<Response<Int>>

    /**
     * 登录状态下重置登录密码
     * @method POST
     * @return view int
     */
    @POST
    fun modifyPassword(
        @Url url: String,
        @Body body: RequestBody
    ): Observable<Response<Int>>

    //---------------------------------------------账户模块----------------------------------------------------------------------

    /**
     * 设置交易密码
     * @method POST
     * @return view int
     */
    @POST
    fun setPayPassword(
        @Url url: String,
        @Body body: RequestBody
    ): Observable<Response<Int>>

    /**
     * 修改交易密码
     * @method POST
     * @return view int
     */
    @POST
    fun modifyPayPassword(
        @Url url: String,
        @Body body: RequestBody
    ): Observable<Response<Int>>

    /**
     * 重置交易密码
     * @method POST
     * @return view int
     */
    @POST
    fun resetPayPassword(
        @Url url: String,
        @Body body: RequestBody
    ): Observable<Response<Int>>

    /**
     * 设置交易密码验证码
     * @method POST
     * @return view int
     */
    @POST
    fun sendSetPayPasswordPvc(
        @Url url: String
    ): Observable<Response<Int>>

    /**
     * 修改交易密码验证码
     * @method POST
     * @return view int
     */
    @POST
    fun sendModifyPayPasswordPvc(
        @Url url: String
    ): Observable<Response<Int>>

    /**
     * 重置交易密码验证码
     * @method POST
     * @return view int
     */
    @POST
    fun sendResetPayPasswordPvc(
        @Url url: String
    ): Observable<Response<Int>>

    /**
     * 获取用户基本信息
     * @method POST
     * @return view int
     */
    @POST
    fun getBasic(
        @Url url: String
    ): Observable<AccountBasicInfoView>

    /**
     * 获取用户基本安全信息
     * @method POST
     * @return view int
     */
    @POST
    fun securityBasic(
        @Url url: String
    ): Observable<AccountSecurityInfoView>

}
