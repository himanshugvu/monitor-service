package com.healthmonitor.monitor.service;

import com.healthmonitor.monitor.model.AppConfiguration;
import com.healthmonitor.monitor.repository.AppConfigurationRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DataInitializationService {

    @Autowired
    private AppConfigurationRepository appConfigRepository;

    @Autowired
    private AppConfigCacheService cacheService;

    @PostConstruct
    public void initializeDefaultApps() {
        // Check if data already exists
        if (appConfigRepository.count() > 0) {
            // Initialize cache if data exists
            cacheService.refreshCache();
            return;
        }

        // Create default app configurations
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
}