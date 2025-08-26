package com.healthmonitor.monitor.model;

public class AppInfo {
    private String appCode;
    private String appName;
    
    public AppInfo() {}
    
    public AppInfo(String appCode, String appName) {
        this.appCode = appCode;
        this.appName = appName;
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
}