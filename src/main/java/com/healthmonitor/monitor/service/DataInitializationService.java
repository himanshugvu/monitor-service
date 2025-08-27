package com.healthmonitor.monitor.service;

import com.healthmonitor.monitor.model.*;
import com.healthmonitor.monitor.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DataInitializationService {

    @Autowired
    private AppConfigurationRepository appConfigRepository;

    @Autowired
    private CountryConfigRepository countryConfigRepository;
    
    @Autowired
    private AppCodeConfigRepository appCodeConfigRepository;

    @Autowired
    private AppConfigCacheService cacheService;

    @PostConstruct
    public void initializeDefaultApps() {
        // Check if data already exists
        if (appConfigRepository.count() > 0 && countryConfigRepository.count() > 0) {
            // Initialize cache if data exists
            cacheService.refreshCache();
            return;
        }

        // Create default configurations
        createDefaultCountryConfigurations();
        createDefaultAppCodeConfigurations();
        createDefaultAppConfigurations();
        
        // Initialize cache after data creation
        cacheService.refreshCache();
    }

    private void createDefaultAppConfigurations() {
        // Get host configuration from environment variables
        String app101Host = System.getenv().getOrDefault("APP_101_HOST", "localhost");
        String app102Host = System.getenv().getOrDefault("APP_102_HOST", "localhost");
        String app103Host = System.getenv().getOrDefault("APP_103_HOST", "localhost");
        String app104Host = System.getenv().getOrDefault("APP_104_HOST", "localhost");
        String app105Host = System.getenv().getOrDefault("APP_105_HOST", "localhost");

        AppConfiguration app101 = new AppConfiguration(
                "APP-101", 
                "Application 101",
                Arrays.asList("http://" + app101Host + ":8081")
        );
        app101.setDescription("Primary application service handling user authentication");

        AppConfiguration app102 = new AppConfiguration(
                "APP-102", 
                "Application 102",
                Arrays.asList("http://" + app102Host + ":8082")
        );
        app102.setDescription("Payment processing service");

        AppConfiguration app103 = new AppConfiguration(
                "APP-103", 
                "Application 103",
                Arrays.asList("http://" + app103Host + ":8083")
        );
        app103.setDescription("Inventory management system");

        AppConfiguration app104 = new AppConfiguration(
                "APP-104", 
                "Application 104",
                Arrays.asList("http://" + app104Host + ":8084")
        );
        app104.setDescription("Notification service");

        AppConfiguration app105 = new AppConfiguration(
                "APP-105", 
                "Application 105",
                Arrays.asList("http://" + app105Host + ":8085")
        );
        app105.setDescription("Analytics and reporting service");

        // Save all configurations
        appConfigRepository.saveAll(Arrays.asList(app101, app102, app103, app104, app105));
    }

    private void createDefaultCountryConfigurations() {
        // Create sample countries
        CountryConfig usa = new CountryConfig("US", "United States", 
            Arrays.asList("APP-101", "APP-102", "APP-103"));
        CountryConfig canada = new CountryConfig("CA", "Canada", 
            Arrays.asList("APP-104", "APP-105"));
        
        countryConfigRepository.saveAll(Arrays.asList(usa, canada));
    }

    private void createDefaultAppCodeConfigurations() {
        // Create app code configurations for US
        AppCodeConfig app101Config = new AppCodeConfig("APP-101", "Application 101", "US", 
            Arrays.asList("APP-101-INSTANCE-1"));
        AppCodeConfig app102Config = new AppCodeConfig("APP-102", "Application 102", "US", 
            Arrays.asList("APP-102-INSTANCE-1"));
        AppCodeConfig app103Config = new AppCodeConfig("APP-103", "Application 103", "US", 
            Arrays.asList("APP-103-INSTANCE-1"));
        
        // Create app code configurations for CA
        AppCodeConfig app104Config = new AppCodeConfig("APP-104", "Application 104", "CA", 
            Arrays.asList("APP-104-INSTANCE-1"));
        AppCodeConfig app105Config = new AppCodeConfig("APP-105", "Application 105", "CA", 
            Arrays.asList("APP-105-INSTANCE-1"));
        
        appCodeConfigRepository.saveAll(Arrays.asList(app101Config, app102Config, app103Config, 
                                                     app104Config, app105Config));
    }
}