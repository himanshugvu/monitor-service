package com.healthmonitor.monitor.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "app_code_configs")
public class AppCodeConfig {
    @Id
    private String id;
    private String appCode;
    private String appName;
    private String countryCode;
    private List<String> appInstances;

    public AppCodeConfig() {}

    public AppCodeConfig(String appCode, String appName, String countryCode, List<String> appInstances) {
        this.appCode = appCode;
        this.appName = appName;
        this.countryCode = countryCode;
        this.appInstances = appInstances;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getAppCode() { return appCode; }
    public void setAppCode(String appCode) { this.appCode = appCode; }

    public String getAppName() { return appName; }
    public void setAppName(String appName) { this.appName = appName; }

    public String getCountryCode() { return countryCode; }
    public void setCountryCode(String countryCode) { this.countryCode = countryCode; }

    public List<String> getAppInstances() { return appInstances; }
    public void setAppInstances(List<String> appInstances) { this.appInstances = appInstances; }
}