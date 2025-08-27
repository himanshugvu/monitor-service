package com.healthmonitor.monitor.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "country_configs")
public class CountryConfig {
    @Id
    private String id;
    private String countryCode;
    private String countryName;
    private List<String> appCodes;

    public CountryConfig() {}

    public CountryConfig(String countryCode, String countryName, List<String> appCodes) {
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.appCodes = appCodes;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getCountryCode() { return countryCode; }
    public void setCountryCode(String countryCode) { this.countryCode = countryCode; }

    public String getCountryName() { return countryName; }
    public void setCountryName(String countryName) { this.countryName = countryName; }

    public List<String> getAppCodes() { return appCodes; }
    public void setAppCodes(List<String> appCodes) { this.appCodes = appCodes; }
}