package com.healthmonitor.monitor.model;

public class AppInfo {
    private String appCode;
    private String appName;
    private String countryCode;
    
    public AppInfo() {}
    
    public AppInfo(String appCode, String appName) {
        this.appCode = appCode;
        this.appName = appName;
    }
    
    public AppInfo(String appCode, String appName, String countryCode) {
        this.appCode = appCode;
        this.appName = appName;
        this.countryCode = countryCode;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}