package com.healthmonitor.monitor.model;

import java.time.LocalDateTime;
import java.util.List;

public class HealthSummary {
    private int totalApps;
    private int healthyApps;
    private int failedApps;
    private List<AppHealth> apps;
    private LocalDateTime lastUpdated;

    public HealthSummary() {}

    public HealthSummary(int totalApps, int healthyApps, int failedApps, List<AppHealth> apps) {
        this.totalApps = totalApps;
        this.healthyApps = healthyApps;
        this.failedApps = failedApps;
        this.apps = apps;
        this.lastUpdated = LocalDateTime.now();
    }

    public int getTotalApps() {
        return totalApps;
    }

    public void setTotalApps(int totalApps) {
        this.totalApps = totalApps;
    }

    public int getHealthyApps() {
        return healthyApps;
    }

    public void setHealthyApps(int healthyApps) {
        this.healthyApps = healthyApps;
    }

    public int getFailedApps() {
        return failedApps;
    }

    public void setFailedApps(int failedApps) {
        this.failedApps = failedApps;
    }

    public List<AppHealth> getApps() {
        return apps;
    }

    public void setApps(List<AppHealth> apps) {
        this.apps = apps;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}