package com.common.ducis.exception;


import org.litepal.crud.LitePalSupport;

import java.util.Date;

/**
 * @describe：
 * @author：ftt
 * @date：2019/11/8
 */
public class ExceptionBean extends LitePalSupport {
    /**
     * @Field("accountNumber")accountNumber:String?
     *                      ,@Field("deviceName")deviceName:String?
     *                      ,@Field("exceptionName")exceptionName:String?
     *                      ,@Field("exceptionDetail")exceptionDetail:String?
     *                      ,@Field("imei")imei:String?
     *                      ,@Field("manufacturer")manufacturer:String?
     *                      ,@Field("systemVersion")systemVersion:String?
     *                      ,@Field("version")version:String?
     *                      ,@Field("time")
     */
    /**
     * 当前应用版本
     */
    private long appVersionId;
    /**
     * 异常名称
     */
    private String exceptionName;
    /**
     * 异常详情
     */
    private String exceptionDetail;
    /**
     * 异常发生时间
     */
    private Date time;
    /**
     * 设备名称
     */
    private String deviceName;
    /**
     * 设备厂商
     */
    private String manufacturer;
    /**
     * 设备唯一识别码
     */
    private String imei;
    /**
     * android系统版本号
     */
    private String systemVersion;
    /**
     * 用户身份识别码
     */
    private String clientId;

    private String authId;

    /**
     * 项目唯一标识
     */
    private String projectName;

    /**
     * 项目秘钥
     */
    private String secret;


    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }

    public String getExceptionDetail() {
        return exceptionDetail;
    }

    public void setExceptionDetail(String exceptionDetail) {
        this.exceptionDetail = exceptionDetail;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public long getAppVersionId() {
        return appVersionId;
    }

    public void setAppVersionId(long appVersionId) {
        this.appVersionId = appVersionId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public String toString() {
        return "ExceptionBean{" +
                "appVersionId=" + appVersionId +
                ", exceptionName='" + exceptionName + '\'' +
                ", exceptionDetail='" + exceptionDetail + '\'' +
                ", time=" + time +
                ", deviceName='" + deviceName + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", imei='" + imei + '\'' +
                ", systemVersion='" + systemVersion + '\'' +
                ", clientId='" + clientId + '\'' +
                ", authId='" + authId + '\'' +
                ", projectName='" + projectName + '\'' +
                ", secret='" + secret + '\'' +
                '}';
    }
}
