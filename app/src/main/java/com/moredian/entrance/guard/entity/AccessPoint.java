package com.moredian.entrance.guard.entity;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/7/29 13:48
 */
public class AccessPoint {
    private String ssid;
    private String EncryptionType;
    private String password;

    public AccessPoint(String ssid, String encryptionType, String password) {
        this.ssid = ssid;
        EncryptionType = encryptionType;
        this.password = password;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getEncryptionType() {
        return EncryptionType;
    }

    public void setEncryptionType(String encryptionType) {
        EncryptionType = encryptionType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
