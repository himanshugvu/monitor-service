package com.healthmonitor.monitor.model;

import java.time.LocalDateTime;
import java.util.List;

public class SystemConfig {
    private List<CountryConfig> countries;
    private List<AppCodeConfig> appCodes;
    private List<AppConfiguration> apps;
    private LocalDateTime lastUpdated;

    public SystemConfig() {}

    public SystemConfig(List<CountryConfig> countries, List<AppCodeConfig> appCodes, 
                       List<AppConfiguration> apps, LocalDateTime lastUpdated) {
        this.countries = countries;
        this.appCodes = appCodes;
        this.apps = apps;
        this.lastUpdated = lastUpdated;
    }

    // Getters and setters
    public List<CountryConfig> getCountries() { return countries; }
    public void setCountries(List<CountryConfig> countries) { this.countries = countries; }

    public List<AppCodeConfig> getAppCodes() { return appCodes; }
    public void setAppCodes(List<AppCodeConfig> appCodes) { this.appCodes = appCodes; }

    public List<AppConfiguration> getApps() { return apps; }
    public void setApps(List<AppConfiguration> apps) { this.apps = apps; }

    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }
}