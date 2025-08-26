package com.healthmonitor.monitor.model;

import java.time.LocalDateTime;
import java.util.List;

public class AppHealth {
    private String appCode;
    private String overallStatus;
    private List<PodHealth> pods;
    private LocalDateTime lastChecked;

    public AppHealth() {}

    public AppHealth(String appCode, String overallStatus, List<PodHealth> pods) {
        this.appCode = appCode;
        this.overallStatus = overallStatus;
        this.pods = pods;
        this.lastChecked = LocalDateTime.now();
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getOverallStatus() {
        return overallStatus;
    }

    public void setOverallStatus(String overallStatus) {
        this.overallStatus = overallStatus;
    }

    public List<PodHealth> getPods() {
        return pods;
    }

    public void setPods(List<PodHealth> pods) {
        this.pods = pods;
    }

    public LocalDateTime getLastChecked() {
        return lastChecked;
    }

    public void setLastChecked(LocalDateTime lastChecked) {
        this.lastChecked = lastChecked;
    }
}