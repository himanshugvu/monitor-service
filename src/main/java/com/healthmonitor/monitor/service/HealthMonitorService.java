package com.healthmonitor.monitor.service;

import com.healthmonitor.monitor.model.AppConfiguration;
import com.healthmonitor.monitor.model.AppHealth;
import com.healthmonitor.monitor.model.HealthSummary;
import com.healthmonitor.monitor.model.PodHealth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.*;

@Service
public class HealthMonitorService {
    
    private final WebClient webClient;
    
    @Autowired
    private AppConfigCacheService appConfigCacheService;
    
    public HealthMonitorService() {
        this.webClient = WebClient.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(1024 * 1024))
                .build();
    }
    
    public HealthSummary getHealthSummary() {
        List<AppHealth> apps = new ArrayList<>();
        int healthy = 0;
        int failed = 0;
        
        Map<String, List<String>> appUrlMappings = appConfigCacheService.getAllAppUrlMappings();
        
        for (Map.Entry<String, List<String>> entry : appUrlMappings.entrySet()) {
            AppHealth appHealth = checkAppHealth(entry.getKey(), entry.getValue());
            apps.add(appHealth);
            
            if ("UP".equals(appHealth.getOverallStatus())) {
                healthy++;
            } else {
                failed++;
            }
        }
        
        return new HealthSummary(apps.size(), healthy, failed, apps);
    }
    
    public AppHealth getAppHealth(String appCode) {
        AppConfiguration config = appConfigCacheService.getAppConfiguration(appCode);
        if (config == null || !config.isEnabled()) {
            return new AppHealth(appCode, "UNKNOWN", Collections.emptyList());
        }
        
        return checkAppHealth(appCode, config.getUrls());
    }
    
    private AppHealth checkAppHealth(String appCode, List<String> urls) {
        List<PodHealth> podHealths = new ArrayList<>();
        boolean allHealthy = true;
        
        for (String url : urls) {
            PodHealth podHealth = checkPodHealth(url);
            podHealths.add(podHealth);
            
            if (!"UP".equals(podHealth.getStatus())) {
                allHealthy = false;
            }
        }
        
        String overallStatus = allHealthy ? "UP" : "DOWN";
        return new AppHealth(appCode, overallStatus, podHealths);
    }
    
    private PodHealth checkPodHealth(String baseUrl) {
        String healthUrl = baseUrl + "/actuator/health";
        long startTime = System.currentTimeMillis();
        
        try {
            String response = webClient.get()
                    .uri(healthUrl)
                    .retrieve()
                    .bodyToMono(String.class)
                    .timeout(Duration.ofSeconds(5))
                    .block();
            
            long responseTime = System.currentTimeMillis() - startTime;
            
            if (response != null && response.contains("\"status\":\"UP\"")) {
                return new PodHealth(baseUrl, "UP", responseTime);
            } else {
                return new PodHealth(baseUrl, "DOWN", responseTime);
            }
        } catch (Exception e) {
            long responseTime = System.currentTimeMillis() - startTime;
            return new PodHealth(baseUrl, "DOWN", responseTime);
        }
    }
}